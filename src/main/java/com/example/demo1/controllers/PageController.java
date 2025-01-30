package com.example.demo1.controllers;

import com.example.demo1.dtos.user.UserProfile;
import com.example.demo1.dtos.user.UserUpdateReqDto;
import com.example.demo1.helpers.FileHelper;
import com.example.demo1.mappers.UserMapper;
import com.example.demo1.models.Job;
import com.example.demo1.models.User;
import com.example.demo1.services.impls.CompanyServiceImpl;
import com.example.demo1.services.impls.JobServiceImpl;
import com.example.demo1.services.impls.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Controller
public class PageController {

    private final UserServiceImpl userServiceImpl;
    private final JobServiceImpl jobService;
    private final UserMapper userMapper;
    private final CompanyServiceImpl companyService;
    private final FileHelper fileHelper;



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

    @PostMapping("/profile/upload-cv")
    public String uploadCV(
            @RequestParam("cv") MultipartFile file,
            @SessionAttribute("user") UserProfile userProfile,
            RedirectAttributes redirectAttributes) {

        if (userProfile == null) {
            redirectAttributes.addFlashAttribute("error", "You need to be logged in to upload a CV.");
            return "redirect:/login";
        }

        try {
            User user = userMapper.toEntity(userProfile);
            String folder = "uploads/cvs";
            String fileName = file.getOriginalFilename();
            String newFileName = fileHelper.uploadFile(folder, fileName, file.getBytes());

            if (newFileName != null) {
                user.setCvFileName(newFileName);
                userProfile.setCvFileName(newFileName);
                userServiceImpl.save(user);
                redirectAttributes.addFlashAttribute("success", "CV uploaded successfully.");
            } else {
                redirectAttributes.addFlashAttribute("error", "Failed to upload CV.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred: " + e.getMessage());
        }

        return "redirect:/profile";
    }

    @GetMapping("/profile/cv/{fileName}")
    public ResponseEntity<Resource> getCV(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get("uploads/cvs").resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                throw new FileNotFoundException("CV not found: " + fileName);
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/jobs/view/{id}/apply")
    public String applyForJob(@PathVariable Long id,
                              @SessionAttribute ("user") UserProfile user) {
        if (user == null) {
            return "redirect:/login";
        }


        User existingUser = userServiceImpl.find(user.getId());
        if (existingUser == null) {
            return "redirect:/login";
        }

        Job existingJob = jobService.findById(id);
        if (existingJob == null) {
            throw new EntityNotFoundException("Job not found with ID: " + id);
        }

        // Add user to job's applicants list and job to user's jobsApplied list
        existingJob.getApplicants().add(existingUser);
        existingUser.getJobsApplied().add(existingJob);

        // Save changes
        jobService.saveJob(existingJob);
        userServiceImpl.save(existingUser);

        return "redirect:/jobs-list";
    }

    @PostMapping("/profile/{id}/cv/upload-cv")
    public String uploadCV(@PathVariable Long id, @RequestParam("cv") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            User user = userServiceImpl.find(id);
            String folder = "uploads/cvs";
            String fileName = file.getOriginalFilename();
            String newFileName = fileHelper.uploadFile(folder, fileName, file.getBytes());
            user.setCvFileName(newFileName);
            userServiceImpl.save(user);
            redirectAttributes.addFlashAttribute("success", "CV uploaded successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred: " + e.getMessage());
        }
        return "redirect:/profile";
    }






    @GetMapping("/jobs")
    public String jobs(Model model) {
        model.addAttribute("jobs", jobService.findAll());
        return "jobs-list";
    }

    @GetMapping("/companies")
    public String companies(Model model) {
        model.addAttribute("companies", companyService.findAll());
        return "companies_list";
    }

    @GetMapping("/companies/{id}")
    public String companyDetails(@PathVariable Long id, Model model) {

        model.addAttribute("company", companyService.findById(id));
        return "company_details";
    }


}
