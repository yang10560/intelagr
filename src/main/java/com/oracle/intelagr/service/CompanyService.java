package com.oracle.intelagr.service;

import com.oracle.intelagr.entity.Company;

import java.util.List;

public interface CompanyService {

    /**
     * 所有公司
     */

    List<Company> getAllCompanys();

    public Company getCompany(String companyCode);
    public List<Company> getCompanyListByCompanyType(String companyType);
}
