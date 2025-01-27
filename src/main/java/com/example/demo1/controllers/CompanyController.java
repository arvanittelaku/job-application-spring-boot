package com.example.demo1.controllers;

import com.example.demo1.dtos.company.CompanyRegisterDto;
import com.example.demo1.services.impls.CompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyServiceImpl companyService;

    @GetMapping("/register/company")
    public String companyRegister(Model model) {
        model.addAttribute("companyRegisterDto", new CompanyRegisterDto());
        return "company-register";
    }
}
