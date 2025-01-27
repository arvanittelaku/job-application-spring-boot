package com.example.demo1.services.impls;

import com.example.demo1.models.Company;
import com.example.demo1.services.CompanyServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyServices {

    @Override
    public List<Company> findAll() {

        return List.of();
    }
}
