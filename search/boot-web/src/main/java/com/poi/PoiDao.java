package com.poi;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Poi;


public interface PoiDao extends JpaRepository<Poi, Integer> {
}

