package com.oracle.intelagr.mapper;

import com.oracle.intelagr.entity.CommonData;

import java.util.List;
import java.util.Map;


public interface CommonDataMapper {
	public List<CommonData> select(Map<String, Object> map);
}
