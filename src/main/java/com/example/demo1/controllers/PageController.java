package com.example.demo1.controllers;

import com.example.demo1.dtos.user.UserProfile;
import com.example.demo1.mappers.UserMapper;
import com.example.demo1.models.User;
import com.example.demo1.services.impls.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PageController {

    private UserServiceImpl userServiceImpl;
    private UserMapper userMapper;

    public PageController() {
    }

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

    @GetMapping("/profile/update")
    public String updateProfileForm(HttpSession session, Model model) {
        model.addAttribute("user", session.getAttribute("user"));
        return "profile-update";
    }

    @PostMapping("/profile/update")
    public String updateProfile(
            @Valid @ModelAttribute("userProfile") UserProfile userProfile,
            BindingResult result,
            HttpSession session,
            Model model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error ->
                    System.out.println("Validation Error: " + error.getDefaultMessage()));
            return "profile_update";
        }

        try {

            UserProfile currentUser = (UserProfile) session.getAttribute("user");
            if (currentUser == null) {
                return "redirect:/login";
            }

            userProfile.setId(currentUser.getId());
            User updatedUser = userServiceImpl.updateProfile(userProfile);
            UserProfile updatedProfile = userMapper.toUserProfile(updatedUser); // Convert User to UserProfile
            session.setAttribute("user", updatedProfile);

            redirectAttributes.addFlashAttribute("success", "Profile updated successfully.");
            return "redirect:/profile";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred while updating your profile.");
            return "redirect:/profile/update";
        }
    }
}
