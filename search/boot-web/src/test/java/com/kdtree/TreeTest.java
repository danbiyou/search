package com.kdtree;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TreeTest {

	@Test
	public void testTree() {
		Tree test = new Tree();
	}

	@Test
	public void testIsEmpty() {
		Tree test = new Tree();
		assertTrue(test.isEmpty());
		
	}

	@Test
	public void testInsert() {
		Tree tree = new Tree();
		double []a =  {1.2,3.0};
		//tree.root = new Node("node1",a);
		Node n = new Node("node1",a);
		tree.insert(n);
		assertEquals(tree.root.data[0]+"",n.data[0]+"");
		
	}

	
	@Test
	public void testRangeSearchRectangle() {
		Tree tree = new Tree();
		//Node node = new Node("node1",new double[]({1.2,1.5}));
		double []a =  {1.2,3.0};
		double []b =  {2.5,3.4};
		double []c =  {4.2,5.0};
		
		tree.insert(new Node("node1",a));
		tree.insert(new Node("node1",b));
		tree.insert(new Node("node1",c));
		
		Rectangle r = new Rectangle(0.0, 2.6, 3.4, 6.0);
		List<Map<String,String>> result = tree.rangeSearch(r);
		assertEquals(1, result.size());
	}


	@Test
	public void testNode2Map() {
		Tree tree = new Tree();
		double []a =  {1.2,3.0};
		Node node = new Node("test",a);
		assertEquals(tree.node2Map(node).get("longitude"),"1.2");
	}


}
