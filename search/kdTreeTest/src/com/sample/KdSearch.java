package com.sample;

import java.util.ArrayList;

public class KdSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		insert(0,2);
		insert(1,2);
		insert(2,2);
		insert(3,2);
		insert(4,2);
		insert(0,3);
		insert(1,3);
		insert(4,3);
		insert(2,4);
		insert(5,4);
		insert(2,0);
		insert(3,0);
		insert(4,0);
		
		
		
		
		
		ArrayList<Point> list = new ArrayList<>();
		
		list.add(new Point(0,2));
		list.add(new Point(1,2));
		list.add(new Point(2,2));
		list.add(new Point(3,2));
		list.add(new Point(4,2));
		list.add(new Point(0,3));
		list.add(new Point(1,3));
		list.add(new Point(4,3));
		list.add(new Point(2,4));
		list.add(new Point(5,4));
		list.add(new Point(2,0));
		list.add(new Point(3,0));
		list.add(new Point(4,0));
	}
	
	public static void insert(int x, int y){
	}

}
