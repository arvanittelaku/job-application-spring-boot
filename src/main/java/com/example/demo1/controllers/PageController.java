package com.example.demo1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

}
