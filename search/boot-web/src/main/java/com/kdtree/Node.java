package com.kdtree;

/**
 * @author yumin
 *
 */
public class Node {
	public double[] data;
	public String name;
	public Node left;
	public Node right;

	/**
	 * Creates New object representing a node
	 */
	public Node() {
		left = null;
		right = null;
	}

	/**
	 * Creates New object representing a node
	 * @param x
	 */
	public Node(double[] x) {
		left = null;
		right = null;
		data = new double[2];
		for (int k = 0; k < 2; k++)
			data[k] = x[k];
	}
	
	/**
	 * Creates New object representing a node
	 * @param name
	 * @param x
	 */
	public Node(String name, double[] x) {
		this.name = name;
		left = null;
		right = null;
		data = new double[2];
		for (int k = 0; k < 2; k++)
			data[k] = x[k];
	}
	

}