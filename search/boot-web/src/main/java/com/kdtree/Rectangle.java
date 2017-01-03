package com.kdtree;

public class Rectangle {
	
	double x1, x2, y1, y2;

	public Rectangle(double a, double b, double c, double d) {
		x1 = a;
		x2 = b;
		y1 = c;
		y2 = d;
	}

	boolean contain(double x, double y) {
		return (x1 <= x && x <= x2 && y1 <= y && y <= y2);
	}
	
}
