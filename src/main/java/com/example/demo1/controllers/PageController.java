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
        model.addAttribute("user", session.getAttribute("user"));
        return "profile_update";
    }

    @PostMapping("/profile/update")
    public String updateProfile(
            @Valid @ModelAttribute("userProfile") UserUpdateReqDto userUpdateReqDto,
            BindingResult result,
            HttpSession session,
            Model model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error ->
                    System.out.println("Validation Error: " + error.getDefaultMessage()));
            return "profile_update";
        }

        //Save the current profile from the session and check if it exists
        UserProfile currentProfile = (UserProfile) session.getAttribute("user");
        if (currentProfile == null) {
            return "redirect:/login";
        }
        //check if the user with the current id of currentProfile exists
        User user = userServiceImpl.findById(currentProfile.getId());
        if (user == null) {
            return "redirect:/login";
        }
        //update the user
        userMapper.updateUserFromDto(userUpdateReqDto,user);
        //save the user
        userServiceImpl.add(user);

        //map the updatedUser to the currentProfile
        UserProfile updatedProfile = userMapper.toProfileDto(user);
        session.setAttribute("user", updatedProfile);

        redirectAttributes.addFlashAttribute("success", "Profile updated successfully.");
        return "redirect:/profile";


    }
}
