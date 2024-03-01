package com.cibertec.assessment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class DibujarPoli2 extends JPanel {

	 
	    // Coordenadas de los vértices del polígono
	    int sides = 5;  // Número de lados del polígono
	    double[] xpoints = new double[sides];
	    double[] ypoints = new double[sides];

	    // Coordenadas del cuadrado
	    double[] squareXPoints = {150, 250, 250, 150, 150};
	    double[] squareYPoints = {150, 150, 250, 250, 150};

	    public DibujarPoli2() {
	        calcularCoordenadasRegulares();
	    }

	    private void calcularCoordenadasRegulares() {
	        double radio = 100.0;  // Radio del polígono

	        for (int i = 0; i < sides; i++) {
	            double angle = 2 * Math.PI * i / sides;
	            xpoints[i] = 200 + radio * Math.cos(angle);
	            ypoints[i] = 200 + radio * Math.sin(angle);
	        }
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;

	        // Dibujar el polígono
	        g2d.setColor(Color.BLUE);
	        g2d.drawPolygon(toIntArray(xpoints), toIntArray(ypoints), sides);

	        // Dibujar el cuadrado dentro del polígono
	        g2d.setColor(Color.RED);
	        g2d.drawPolygon(toIntArray(squareXPoints), toIntArray(squareYPoints), squareXPoints.length);
	    }

	    private int[] toIntArray(double[] array) {
	        int[] intArray = new int[array.length];
	        for (int i = 0; i < array.length; i++) {
	            intArray[i] = (int) array[i];
	        }
	        return intArray;
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            JFrame frame = new JFrame("Dibujar Polígono con Cuadrado");
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.add(new DibujarPoli2());
	            frame.setSize(400, 400);
	            frame.setLocationRelativeTo(null);
	            frame.setVisible(true);
	        });
	    }

}
