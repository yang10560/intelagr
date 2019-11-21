package com.oracle.intelagr.mapper;

import com.oracle.intelagr.entity.SeedVariety;

import java.util.List;
import java.util.Map;



public interface SeedVarietyMapper {
	public List<SeedVariety> select(Map<String, Object> map);
}
