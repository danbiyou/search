package com.kdtree;

public class Node {
	public double[] data;
	public String name;
	public Node left;
	public Node right;

	public Node() {
		left = null;
		right = null;
	}

	public Node(double[] x) {
		left = null;
		right = null;
		data = new double[2];
		for (int k = 0; k < 2; k++)
			data[k] = x[k];
	}
	
	public Node(String name, double[] x) {
		this.name = name;
		left = null;
		right = null;
		data = new double[2];
		for (int k = 0; k < 2; k++)
			data[k] = x[k];
	}
	

}