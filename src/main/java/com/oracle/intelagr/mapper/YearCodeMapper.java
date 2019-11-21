package com.oracle.intelagr.mapper;

import com.oracle.intelagr.entity.YearCode;

import java.util.List;
import java.util.Map;




public interface YearCodeMapper {
	public List<YearCode> select(Map<String, Object> map);
}
