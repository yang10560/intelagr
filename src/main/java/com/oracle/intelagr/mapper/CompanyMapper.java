package com.oracle.intelagr.mapper;

import com.oracle.intelagr.entity.Company;

import java.util.List;
import java.util.Map;

public interface CompanyMapper {

    /**
     * 所有公司
     */

    List<Company> getAllCompanys();

    public List<Company> select(Map<String,Object> map);
}
