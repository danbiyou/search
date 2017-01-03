package com.kdtree;

import static org.junit.Assert.*;

import org.junit.Test;

public class RectangleTest {

	@Test
	public void testRectangle() {
		Rectangle r = new Rectangle(1.0, 2.0, 3.0, 4.0);
	}

	@Test
	public void testContain() {
		Rectangle r = new Rectangle(1.0, 2.0, 3.0, 4.0);
		assertTrue(r.contain(1.5, 3.3));
		assertFalse( r.contain(0.8, 3.3));
	}

}
