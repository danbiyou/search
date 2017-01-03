package com.kdtree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tree {
	Node root;
	int axis = 0;
	int dimension = 2;

	public Tree() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void insert(Node n) {
		root = insert(n, root, axis);
	}

	private Node insert(Node new_node, Node root, int axis) {
		if (root == null)
			root = new Node(new_node.name, new_node.data);
		
		else if (new_node.data[axis] < root.data[axis])
			root.left = insert(new_node, root.left, (axis + 1) % dimension);
		else
			root.right = insert(new_node, root.right, (axis + 1) % dimension);
		return root;
	}

	public List<Map<String,String>> rangeSearch(Rectangle r) {
		return rangeSearch(root, r, 0);
	}

	public List<Map<String,String>> rangeSearch(Node t, Rectangle r, int axis) {
		//List<Node> result = new ArrayList<Node>();
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
		//System.out.println("left = "+left +"       right="+right+"      median = "+median);
		if ((left <= median && median <= right) && r.contain(t.data[0], t.data[1])) {
			//System.out.println("contain!");
			result.add(node2Map(t));
		}

		if (t.left != null && left < median)
			result.addAll(rangeSearch(t.left, r, axis + 1));
		if (t.right != null && right > median)
			result.addAll(rangeSearch(t.right, r, axis + 1));

		return result;

	}

	public Map<String,String> node2Map(Node n){
		Map<String, String> m = new HashMap<String, String>();
		m.put("name",n.name);
		m.put("longitude",n.data[0]+"");
		m.put("latitude",n.data[1]+"");
		return m;
	}
	public void inorder() {
		inorder(root);
	}

	private void inorder(Node r) {
		if (r != null) {
			inorder(r.left);
			System.out.print("(" + r.data[0] + "," + r.data[1] + ") ");
			inorder(r.right);
		}
	}

	public void preorder() {
		preorder(root);
	}

	private void preorder(Node r) {
		if (r != null) {
			System.out.print("(" + r.data[0] + "," + r.data[1] + ") ");
			preorder(r.left);
			preorder(r.right);
		}
	}

	public void postorder() {
		postorder(root);
	}

	private void postorder(Node r) {
		if (r != null) {
			postorder(r.left);
			postorder(r.right);
			System.out.print("(" + r.data[0] + "," + r.data[1] + ") ");
		}
	}

}