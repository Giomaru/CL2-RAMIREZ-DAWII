package com.cibertec.assessment.service.imp;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.assessment.beans.PolygonBean;
import com.cibertec.assessment.beans.SquareBean;
import com.cibertec.assessment.model.Square;
import com.cibertec.assessment.repo.SquareRepo;
import com.cibertec.assessment.service.PolygonService;
import com.cibertec.assessment.service.SquareService;

@Service
public class SquareServiceImpl implements SquareService{

	@Autowired 
	SquareRepo squareRepo;
	
	@Autowired
	PolygonService polygonService;

	@Override
	public void create(Square s) {
		squareRepo.save(s);
		
	}

	@Override
	public void create(List<Square> ls) {
		squareRepo.saveAll(ls);
		
	}

	@Override
	public List<SquareBean> list() {
		List<Square> list = squareRepo.findAll();
		List<SquareBean> listSquareBeans = new ArrayList<>();
		list.forEach(p -> {
			Integer[] intArrayX = new Integer[5];
			Integer[] intArrayY = new Integer[5];

			convertStringInIntegerArray(p.getXPoints(), p.getYPoints(), intArrayX, intArrayY);

			SquareBean squBean = new SquareBean();
			squBean.setId(p.getId());
			squBean.setName(p.getName());
			squBean.setXPoints(intArrayX);
			squBean.setYPoints(intArrayY);
			squBean.setNpoints(p.getNpoints());

			listSquareBeans.add(squBean);
		});

		return listSquareBeans;
	}
	
	
	private void convertStringInIntegerArray(String xPoints, String yPoints, Integer[] intArrayX, Integer[] intArrayY) {

		String cleanedXPoints = xPoints.substring(1, xPoints.length() - 1);
		String cleanedYPoints = yPoints.substring(1, yPoints.length() - 1);

		// Split the string by commas
		String[] partsX = cleanedXPoints.split(", ");
		String[] partsY = cleanedYPoints.split(", ");

		for (int i = 0; i < partsX.length; i++) {
			intArrayX[i] = Integer.parseInt(partsX[i]);
		}
		
		for (int i = 0; i < partsY.length; i++) {
			intArrayY[i] = Integer.parseInt(partsY[i]);
		}
	}
	
	//Al momento de crear se debe validar si 
	//alguno de parte del cuadrado se encuentra dentro de algun
	//poligono y de ser asi se debe capturar el id de los poligonos y 
	//guardar como un string pero con formato de array
	//Ejemplo polygons = "["1","2"]"
	//Se guardan los ids correspondites
	//usar los metodos ya existentes para listar polygonos

	
	private List<Integer> findIntersectedPolygons(SquareBean square) {
		List<PolygonBean> polygons = polygonService.list(); // Asegúrate de tener un método listPolygons en tu servicio

	    List<Integer> intersectedPolygonIds = new ArrayList<>();
	    for (PolygonBean polygon : polygons) {
	        if (isSquareInsidePolygon(square.getXPoints(), square.getYPoints(), polygon)) {
	            intersectedPolygonIds.add(polygon.getId());
	        }
	    }

	    return intersectedPolygonIds;
	}



	private boolean isSquareInsidePolygon(Integer[] squareXPoints, Integer[] squareYPoints, PolygonBean polygon) {
	    int n = polygon.getNpoints();
	    int j = n - 1;
	    boolean inside = false;

	    for (int i = 0; i < n; i++) {
	        double xi = polygon.getXPoints()[i];
	        double yi = polygon.getYPoints()[i];
	        double xj = polygon.getXPoints()[j];
	        double yj = polygon.getYPoints()[j];

	        for (int k = 0; k < squareXPoints.length; k++) {
	            boolean intersect = ((yi > squareYPoints[k]) != (yj > squareYPoints[k]))
	                    && (squareXPoints[k] < (xj - xi) * (squareYPoints[k] - yi) / (yj - yi) + xi);

	            if (intersect) {
	                inside = !inside;
	            }
	        }

	        j = i;
	    }

	    return inside;
	}

	

    

}
