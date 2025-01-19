package com.example.demo1.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        // Ensure the user is logged in by checking the session
        if (session.getAttribute("user") == null) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }

        // Add the user object from the session to the model
        model.addAttribute("user", session.getAttribute("user"));
        return "profile"; // This should match the name of your HTML file: profile.html
    }

    @PostMapping("/profile")
    public String updateProfile(HttpSession session, Model model) {


        return "redirect:/profile";
    }
}
