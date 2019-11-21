package com.oracle.intelagr.service;

import com.oracle.intelagr.entity.CommonData;

import java.util.List;




public interface ICommonDataService {
	public CommonData getCommonData(String codeKey, String codeCode);
	public List<CommonData> getCommonDataListByCodeKey(String codeKey);
}
