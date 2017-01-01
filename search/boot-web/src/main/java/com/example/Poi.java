package com.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
public class Poi {

	//@Entity
	public class Post {
		
		@Id
		@GeneratedValue
		int id;
		String name;
		String longitude;
		String latitude;
		
		
		//@Column(length = 100000000)
	}

}
