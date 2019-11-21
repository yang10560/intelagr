package com.oracle.intelagr.service;

import com.oracle.intelagr.entity.YearCode;

import java.util.List;


public interface IYearCodeService {
	public YearCode getYearCode(String yearCode) ;
	public List<YearCode> getYearCodeList();
}
