package com.example.demo1.controllers;

import com.example.demo1.dtos.user.UserLoginDto;
import com.example.demo1.dtos.user.UserProfile;
import com.example.demo1.dtos.user.UserRegDto;
import com.example.demo1.exceptions.EmailExistException;
import com.example.demo1.exceptions.UserNotFoundException;
import com.example.demo1.exceptions.UsernameExistsException;
import com.example.demo1.exceptions.WrongPasswordException;
import com.example.demo1.mappers.UserMapperImpl;
import com.example.demo1.models.User;
import com.example.demo1.services.impls.UserServiceImpl;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final UserServiceImpl userServiceImpl;
    private final UserMapperImpl userMapper;


    @GetMapping("/login")
    public String loginForm(Model model) {
        System.out.println("Login page called!");
        model.addAttribute("loginRequestDto", new UserLoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute UserLoginDto loginRequestDto,
                        BindingResult result,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println("Validation Error: " + error.getDefaultMessage()));
            return "login";
        }

        try {
            // Log in the user and retrieve their profile
            User searchUser = userServiceImpl.login(loginRequestDto);

            // Set cookies for username
            Cookie cookie = new Cookie("username", searchUser.getUsername());
            cookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setSecure(false);
            response.addCookie(cookie);

            // Create user session
            HttpSession session = request.getSession();
            session.setAttribute("user", userMapper.toUserProfile(searchUser));

            return "redirect:/index";

        } catch (UserNotFoundException | WrongPasswordException e) {
            redirectAttributes.addFlashAttribute("error", "Username or Password is incorrect!");
            return "redirect:/login";
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "An unexpected error occurred. Please try again.");
            return "redirect:/login";
        }
    }





    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        HttpSession session = request.getSession(false);

        session.invalidate();

        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("userRegDto", new UserRegDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("userRegDto") UserRegDto userRegDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println("Validation Error: " + error.getDefaultMessage()));
            return "register";
        }

        try {
            userServiceImpl.register(userRegDto);

            redirectAttributes.addFlashAttribute("success", "You have been registered successfully.");
            return "redirect:/login";

        } catch (EmailExistException | UsernameExistsException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";

        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "An unexpected error occurred. Please try again.");
            return "redirect:/register";
        }
    }



//    @PostMapping("/profile/update")
//    public String updateProfile(@Valid @ModelAttribute UserProfile userProfile,
//                                BindingResult result,
//                                HttpSession session,
//                                RedirectAttributes redirectAttributes) {
//
//        // Check for validation errors
//        if (result.hasErrors()) {
//            result.getAllErrors().forEach(System.out::println);
//            return "profile_update";
//        }
//
//        try {
//            // Update the user's profile
//            User updatedUser = userServiceImpl.updateProfile(userProfile); // Ensure `updateProfile` updates in DB
//            UserProfile updatedProfile = userMapper.toUserProfile(updatedUser);
//            session.setAttribute("user", updatedProfile);
//
//            redirectAttributes.addFlashAttribute("success", "Profile updated successfully.");
//            return "redirect:/index";
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("error", "An unexpected error occurred. Please try again.");
//            return "redirect:/profile/update";
//        }
//    }



}
