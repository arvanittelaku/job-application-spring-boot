package com.example.demo1.controllers;

import com.example.demo1.dtos.user.UserProfile;
import com.example.demo1.dtos.user.UserUpdateReqDto;
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
        // Ensure the user is logged in by checking the session
        if (session.getAttribute("user") == null) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }
        // Add the user object from the session to the model
        model.addAttribute("user", session.getAttribute("user"));
        return "profile_update";
    }

    @PostMapping("/profile/update")
    public String updateProfile(
            @Valid @ModelAttribute("userProfile") UserUpdateReqDto userUpdateReqDto,
            BindingResult result,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Check for validation errors
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error ->
                    System.out.println("Validation Error: " + error.getDefaultMessage()));
            // Add errors back to the model for displaying in the view
            model.addAttribute("errors", result.getAllErrors());
            return "profile_update";
        }

        // Get the user object from the session
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            redirectAttributes.addFlashAttribute("error", "User not found.");
            return "redirect:/login";
        }

        // Map the UserUpdateReqDto to a UserProfile object

        UserProfile userProfile = userMapper.fromUpdateToProfile(userUpdateReqDto);

        // Update the user object with the form data
        userProfile.setId(currentUser.getId());
        userProfile.setPassword(currentUser.getPassword());
        userProfile.setRole(currentUser.getRole());
        userProfile.setAddress(currentUser.getAddress());
        userProfile.setCity(currentUser.getCity());
        userProfile.setCountry(currentUser.getCountry());
        userProfile.setState(currentUser.getState());
        userProfile.setPostalCode(currentUser.getPostalCode());
        userProfile.setPhone(currentUser.getPhone());
        userProfile.setEmail(currentUser.getEmail());
        userProfile.setProfileImage(currentUser.getProfileImage());

        // Update the user in the database
        User updatedUser = userServiceImpl.updateProfile(userProfile);

        // Update the user object in the session
        session.setAttribute("user", updatedUser);

        return "redirect:/profile";
    }

}
