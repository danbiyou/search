package com.search;
import static org.junit.Assert.*;

import org.junit.Test;

public class SearchPoiTest {

	@Test
	public void testSearch() {
		SearchPoi s = new SearchPoi();
	}

	@SuppressWarnings("static-access")
	@Test
	public void testString2Double() {
		SearchPoi s = new SearchPoi();
		assertEquals("0.0555",(s.string2Double("0.0555"))+"");
		
	}

}
