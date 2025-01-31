package com.example.demo1.services;

import com.example.demo1.dtos.company.CompanyLoginDto;
import com.example.demo1.dtos.company.CompanyRegisterDto;
import com.example.demo1.models.Company;

import java.util.List;

public interface CompanyServices {

    // find all method
    List<Company> findAll();

    // find by id
    Company findById(Long id);

    CompanyRegisterDto register (CompanyRegisterDto companyRegisterDto);

    Company login (CompanyLoginDto companyLoginDto);

    Company save(Company company);
}
