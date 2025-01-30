package com.example.demo1.controllers;

import com.example.demo1.dtos.company.CompanyLoginDto;
import com.example.demo1.dtos.company.CompanyRegisterDto;
import com.example.demo1.dtos.job.JobCreateDto;
import com.example.demo1.exceptions.CompanyExistsException;
import com.example.demo1.exceptions.UserNotFoundException;
import com.example.demo1.exceptions.WrongPasswordException;
import com.example.demo1.mappers.CompanyMapperImpl;
import com.example.demo1.models.Company;
import com.example.demo1.services.impls.CompanyServiceImpl;
import com.example.demo1.services.impls.JobServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyServiceImpl companyServiceImpl;
    private final View error;
    private final CompanyMapperImpl companyMapperImpl;
    private final JobServiceImpl jobServiceImpl;

    @GetMapping("/register/company")
    public String companyRegister(Model model) {
        model.addAttribute("companyRegisterDto", new CompanyRegisterDto());
        return "company-register";
    }

    @PostMapping("/register/company")
    public String registerCompany(
            @Valid @ModelAttribute("companyRegisterDto") CompanyRegisterDto companyRegisterDto,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println("Validation Error: " + error.getDefaultMessage()));
            return "redirect:/register/company";
        }

        try {
            companyServiceImpl.register(companyRegisterDto);

            redirectAttributes.addFlashAttribute("success", "You have been registered successfully.");
            return "redirect:/login/company";
        } catch (CompanyExistsException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register/company";
        }
    }

    @GetMapping("/login/company")
    public String companyLogin(Model model) {
        model.addAttribute("companyLoginDto", new CompanyLoginDto());
        return "company_login";
    }

    @PostMapping("/login/company")
    public String loginCompany(
            @Valid @ModelAttribute("companyLoginDto") CompanyLoginDto companyLoginDto,
            BindingResult result,
            HttpServletRequest request,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println("Validation Error: " + error.getDefaultMessage()));
            return "company_login";
        }

        try {
            Company company = companyServiceImpl.login(companyLoginDto);

            // Set cookies

            Cookie cookie = new Cookie("email", companyLoginDto.getEmail());
            cookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setSecure(false);
            response.addCookie(cookie);

            // Create user session

            HttpSession session = request.getSession();
            session.setAttribute("company", companyMapperImpl.toProfileDto(company));

            return "redirect:/index";
        } catch (UserNotFoundException | WrongPasswordException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/login/company";
        }
    }

    @GetMapping("/jobs-add")
    public String createJobForm(Model model) {
        model.addAttribute("jobCreateDto", new JobCreateDto());
        return "jobs-add";
    }

    @PostMapping("/jobs-add")
    public String createJob(
            @Valid @ModelAttribute("jobCreateDto") JobCreateDto jobCreateDto,
            BindingResult result,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {


        if (result.hasErrors()) {
            // No redirection for validation errors; show the same form with error messages
            return "jobs-add";
        }

        Company company = (Company) request.getSession().getAttribute("company");
        if (company == null) {
            redirectAttributes.addFlashAttribute("error", "You need to be logged in as a company to post jobs.");
            return "redirect:/login/company";
        }

        try {
            jobServiceImpl.add(jobCreateDto);
            redirectAttributes.addFlashAttribute("success", "Job created successfully.");
            return "redirect:/jobs-list"; // Redirect to jobs list after success
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/jobs-add";
        }
    }



    @GetMapping("/jobs-list")
    public String getJobsList(Model model) {
        model.addAttribute("jobs", jobServiceImpl.findAll());
        return "jobs-list";
    }

}
