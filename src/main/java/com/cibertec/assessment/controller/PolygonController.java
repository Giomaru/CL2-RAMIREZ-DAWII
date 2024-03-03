package com.cibertec.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.assessment.beans.PolygonBean;
import com.cibertec.assessment.model.Polygon;
import com.cibertec.assessment.service.PolygonService;

@RestController
@RequestMapping("/polygons")
public class PolygonController {
	
	@Autowired
    private PolygonService polygonService;
	
	
	@PostMapping
    public void createPolygon(@RequestBody Polygon polygon) {
        polygonService.create(polygon);
    }

    @PostMapping("/batch")
    public void createPolygons(@RequestBody List<Polygon> polygons) {
        polygonService.create(polygons);
    }

    @GetMapping
    public List<PolygonBean> listPolygons() {
        return polygonService.list();
    }

	/*
	 * @PostMapping public ResponseEntity<String> createPolygon(@RequestBody Polygon
	 * polygon) { polygonService.create(polygon); return new
	 * ResponseEntity<>("Polygon created successfully", HttpStatus.CREATED); }
	 * 
	 * @PostMapping("/batch") public ResponseEntity<String>
	 * createPolygons(@RequestBody List<Polygon> polygons) {
	 * polygonService.create(polygons); return new
	 * ResponseEntity<>("Polygons created successfully", HttpStatus.CREATED); }
	 * 
	 * @GetMapping public ResponseEntity<List<PolygonBean>> listPolygons() {
	 * List<PolygonBean> polygons = polygonService.list(); return new
	 * ResponseEntity<>(polygons, HttpStatus.OK); }
	 */

}
