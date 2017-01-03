package com.kdtree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Builds a Multi-dimensional Tree
 * @author yumin
 *
 */
public class Tree {
	Node root;
	int axis = 0;
	int dimension = 2;

	/**
	 * Creates Tree Objects
	 */
	public Tree() {
		root = null;
	}

	
	
	/**
	 * Checks the root of tree is empty
	 * @return
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Inserts new node into the tree
	 * @param n
	 */
	public void insert(Node n) {
		root = insert(n, root, axis);
	}

	/**
	 * Inserts new noode into the tree
	 * 
	 * @param new_node which is going to be inserted into the tree
	 * @param root the tree of root
	 * @param axis represents that current level is vertical or horizontal  
	 * @return
	 */
	private Node insert(Node new_node, Node root, int axis) {
		if (root == null)
			root = new Node(new_node.name, new_node.data);
		
		else if (new_node.data[axis] < root.data[axis])
			root.left = insert(new_node, root.left, (axis + 1) % dimension);
		else
			root.right = insert(new_node, root.right, (axis + 1) % dimension);
		return root;
	}

	/**
	 * Gets List of nodes that locate in the region of Rectangle
	 * @param r Range
	 * @return List of nodes
	 */
	public List<Map<String,String>> rangeSearch(Rectangle r) {
		return rangeSearch(root, r, 0);
	}

	/**
	 * Gets List of nodes that locate in the region of Rectangle
	 * @param t the Tree of root
	 * @param r range
	 * @param axis 0=x comparison, 1=y comparison
	 * @return List of nodes
	 */
	public List<Map<String,String>> rangeSearch(Node t, Rectangle r, int axis) {
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		double left, right, median;
		if (axis % 2 == 0) {
			left = r.x1;
			right = r.x2;
			median = t.data[0];
		} else {
			left = r.y1;
			right = r.y2;
			median = t.data[1];
		}
		if ((left <= median && median <= right) && r.contain(t.data[0], t.data[1])) {
			result.add(node2Map(t));
		}

		if (t.left != null && left < median)
			result.addAll(rangeSearch(t.left, r, axis + 1));
		if (t.right != null && right > median)
			result.addAll(rangeSearch(t.right, r, axis + 1));

		return result;

	}

	/**
	 * Converts a Node to a Map object
	 * @param n
	 * @return 
	 */
	public Map<String,String> node2Map(Node n){
		Map<String, String> m = new HashMap<String, String>();
		m.put("name",n.name);
		m.put("longitude",n.data[0]+"");
		m.put("latitude",n.data[1]+"");
		return m;
	}

}