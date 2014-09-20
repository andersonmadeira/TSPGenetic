package org.amad.tsp.algo;

import java.awt.Point;

public class City {
	private Point pt;
	private int id;
	
	private static final int RADIUS = 30;
	private static int nextId = 0;
	
	public City(int pX, int pY) {
		this.pt = new Point(pX, pY);
		this.id = nextId++;
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
	
	public double distanceTo(City c) {
		return Point.distance(getCenterX(), getCenterY(), 
							  c.getCenterX(), c.getCenterY());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getCenterX()+","+getCenterY();
	}
	
	// static methods
	
	public static int getDefaultRadius() {
		return RADIUS;
	}

	public static void resetCityCount() {
		nextId = 0;
	}
}
