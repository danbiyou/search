package com.kdtree;

/**
 * 범위를 지정하는 class
 * @author yumin
 */
public class Rectangle {
	
	double x1, x2, y1, y2;

	/**
	 * Creates new Rectangle Object that represents 2 Longitude and 2 latitude. 
	 * @param a Longitude from
	 * @param b Longitude to
	 * @param c Latitude from
	 * @param d Latitude to
	 */
	public Rectangle(double a, double b, double c, double d) {
		x1 = a;
		x2 = b;
		y1 = c;
		y2 = d;
	}

	/**
	 * Checks whether the rectagle contains the longitude and the latitude
	 * @param x longitude
	 * @param y latitude
	 * @return
	 */
	boolean contain(double x, double y) {
		return (x1 <= x && x <= x2 && y1 <= y && y <= y2);
	}
	
}
