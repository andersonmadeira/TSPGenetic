package org.amad.tsp.algo;

import java.awt.Color;
import java.awt.Point;

public class City {
	private Point pt;
	private int id;
	private String symbol;
	private Color color;
	
	private static final int RADIUS = 30;
	private static int nextId = 0;
	
	public City(int pX, int pY, String symbol) {
		this.pt = new Point(pX, pY);
		this.id = nextId++;
		this.symbol = symbol;
		this.color = new Color((int)(Math.random() * 200) + 50,
							   (int)(Math.random() * 200) + 50,
							   (int)(Math.random() * 200) + 50);
	}

	public void move(Point center) {
		this.pt.x = center.x;
		this.pt.y = center.y;
	}
	
	public void move(int pX, int pY) {
		this.pt.x = pX;
		this.pt.y = pY;
	}
	
	public int getX() {
		return this.pt.x;
	}
	
	public int getY() {
		return this.pt.y;
	}
	
	public int getCenterX() {
		return this.pt.x + (RADIUS / 2);
	}
	
	public int getCenterY() {
		return this.pt.y + (RADIUS / 2);
	}
	
	public int getId() {
		return id;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	/**
	 * Computes the distance between two cities
	 * @param c
	 * @return
	 */
	public double distanceTo(City c) {
		return Point.distance(getCenterX(), getCenterY(), 
							  c.getCenterX(), c.getCenterY());
	}
	
	@Override
	public String toString() {
		return getCenterX()+","+getCenterY();
	}
	
	// static methods
	
	public static int getDefaultRadius() {
		return RADIUS;
	}
	
	/**
	 * Get the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * This method must be called every time we need to generate graph.
	 * So we get just 0 - 9 ids.
	 */
	public static void resetCityCount() {
		nextId = 0;
	}
}
