package com.example.demo1.controllers;

import com.example.demo1.dtos.user.UserProfile;
import com.example.demo1.dtos.user.UserUpdateReqDto;
import com.example.demo1.mappers.UserMapper;
import com.example.demo1.models.User;
import com.example.demo1.services.impls.CompanyServiceImpl;
import com.example.demo1.services.impls.JobServiceImpl;
import com.example.demo1.services.impls.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class PageController {

    private final UserServiceImpl userServiceImpl;
    private final JobServiceImpl jobService;
    private final UserMapper userMapper;
    private final CompanyServiceImpl companyService;



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
            return "redirect:/login";
        }

        model.addAttribute("user", session.getAttribute("user"));
        return "profile";
    }

    @GetMapping("/profile/update")
    public String updateProfileForm(@SessionAttribute("user") UserProfile session, Model model) {

        if (session == null) {
            return "redirect:/login";
        }

        model.addAttribute("userProfile", session);
        return "profile_update";
    }



    @PostMapping("/profile/update")
    public String updateProfile(
            @Valid @ModelAttribute("userUpdateReqDto") UserProfile userProfile,
            BindingResult result,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error ->
                    System.out.println("Validation Error: " + error.getDefaultMessage()));
            model.addAttribute("userProfile", userProfile);
            return "profile_update";
        }

        try {

            UserProfile currentUser = (UserProfile) session.getAttribute("user");
            if (currentUser == null) {
                return "redirect:/login";
            }

            userProfile.setId(currentUser.getId());

            UserProfile updatedUser = userServiceImpl.updateProfile(userProfile);
            //write method to convert updatedUser to UserProfile
            session.setAttribute("user", updatedUser);

            redirectAttributes.addFlashAttribute("success", "Profile updated successfully.");
            return "redirect:/profile";

        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "User not found.");
            return "redirect:/profile/update";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "An error occurred while updating your profile.");
            return "redirect:/profile/update";
        }
    }

    @GetMapping("/jobs")
    public String jobs(Model model) {
        model.addAttribute("jobs", jobService.findAll());
        return "jobs-list";
    }

    @GetMapping("companies")
    public String companies(Model model) {
        model.addAttribute("companies", companyService.findAll());
        return "companies";
    }






}
