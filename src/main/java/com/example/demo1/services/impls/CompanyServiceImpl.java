package com.example.demo1.services.impls;

import com.example.demo1.dtos.company.CompanyLoginDto;
import com.example.demo1.dtos.company.CompanyRegisterDto;
import com.example.demo1.exceptions.CompanyExistsException;
import com.example.demo1.exceptions.WrongPasswordException;
import com.example.demo1.mappers.CompanyMapper;
import com.example.demo1.models.Company;
import com.example.demo1.repositories.CompanyRepository;
import com.example.demo1.services.CompanyServices;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyServices {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Company> findAll() {

        return companyRepository.findAll();
    }

    @Override
    public CompanyRegisterDto register(CompanyRegisterDto companyRegisterDto) {
        if (companyRepository.findByCompanyName(companyRegisterDto.getCompanyName()).isPresent()) {
            throw new CompanyExistsException("Company already exists");
        }

        Company company = companyMapper.fromRegisterToEntity(companyRegisterDto);
        company.setPassword(passwordEncoder.encode(companyRegisterDto.getPassword()));

        var savedCompany = companyRepository.save(company);
        return companyMapper.toRegisterDto(savedCompany);
    }

    @Override
    public Company login(CompanyLoginDto companyLoginDto) {
        Company company = companyRepository.findByEmail(companyLoginDto.getEmail())
                .orElseThrow(() -> new CompanyExistsException("Company not found!"));

        if (!passwordEncoder.matches(companyLoginDto.getPassword(), company.getPassword())) {
            throw new WrongPasswordException("Invalid password!");
        }

        return company;
    }



    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyExistsException("Company not found!"));
    }
}
