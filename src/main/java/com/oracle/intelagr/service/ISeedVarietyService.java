package com.oracle.intelagr.service;

import com.oracle.intelagr.entity.SeedVariety;

import java.util.List;



public interface ISeedVarietyService {
	public SeedVariety getSeedVariety(String seedCode);
	public List<SeedVariety> getSeedVarietyList() ;
}
