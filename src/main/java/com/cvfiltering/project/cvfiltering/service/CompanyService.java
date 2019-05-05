package com.cvfiltering.project.cvfiltering.service;

import com.cvfiltering.project.cvfiltering.entity.Company;

import java.util.List;

public interface CompanyService {
    Company insert(Company company);
    Company update(Company company,Long id);
    Company getOne(Long id);
    void delete(Long id);
    List<Company> getAll();

}
