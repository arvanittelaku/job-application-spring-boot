package com.example.demo1.controllers;

import com.example.demo1.dtos.job.JobCreateDto;
import com.example.demo1.dtos.job.JobDeleteDto;
import com.example.demo1.dtos.job.JobUpdateDto;
import com.example.demo1.dtos.user.UserProfile;
import com.example.demo1.mappers.JobMapper;
import com.example.demo1.mappers.UserMapper;
import com.example.demo1.models.Job;
import com.example.demo1.models.User;
import com.example.demo1.repositories.UserRepository;
import com.example.demo1.services.impls.CompanyServiceImpl;
import com.example.demo1.services.impls.JobServiceImpl;
import com.example.demo1.services.impls.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("crud")
@RequiredArgsConstructor
@Controller
public class JobManagmentController {
    private final JobServiceImpl jobServiceImpl;
    private final UserMapper userMapper;
    private final JobMapper jobMapper;
    private final CompanyServiceImpl companyService;
    private final UserServiceImpl userServiceImpl;
    private final UserRepository userRepository;


//    @GetMapping("/jobs")
//    public String jobs(Model model) {
//        model.addAttribute("jobs", jobService.findAll());
//        return "jobs-list";
//    }

//    @GetMapping("/jobs-add")
//    public String addJob(Model model) {
//        model.addAttribute("jobCreateDto", new JobCreateDto());
//        return "jobs-add";
//    }
//    @PostMapping("/jobs-add")
//    public String addJob(JobCreateDto jobCreateDto) {
//        jobService.add(jobCreateDto);
//        return "jobs-list";
//    }
//
//    @GetMapping("/{id}/delete")
//    public String deleteJob(Model model) {
//        model.addAttribute("jobDeleteDto", new JobCreateDto());
//        return "crud/jobs-delete";
//    }
//    @PostMapping("/{id}/delete")
//    public String deleteJob(@ModelAttribute JobDeleteDto jobDeleteDto) {
//        jobService.remove(jobDeleteDto);
//        return "redirect:/crud/jobs";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String editJob(Model model) {
//        model.addAttribute("jobUpdateDto", new JobUpdateDto());
//        return "crud/jobs-edit";
//    }
//    @PostMapping("/{id}/edit")
//    public String editJob(@ModelAttribute JobUpdateDto jobUpdateDto) {
//        jobService.modify(jobUpdateDto);
//        return "redirect:/crud/jobs";
//    }
//    @GetMapping("/{id}/details")
//    public String detailsJob(@PathVariable long id,Model model) {
//        model.addAttribute("jobDetailsDto", jobService.findById(id));
//        return "crud/jobs-details";
//    }

//    @PostMapping("jobs/view/{id}/apply")
//    public String applyForJob(@PathVariable Long id, @SessionAttribute UserProfile user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "jobs-details";
//        }
//        if (user == null) {
//            return "redirect:/login";
//        }
//
//        // Fetch existing user and job entities
//        User existingUser = userServiceImpl.find(user.getId());
//        if (existingUser == null){
//            return "redirect:/login";
//        }
//
//        Job existingJob = jobServiceImpl.findById(id);
//        if (existingJob == null) {
//            throw new EntityNotFoundException("Job not found with ID: " + id);
//        }
//
//        // Add user to job's applicants list and job to user's jobsApplied list
//        existingJob.getApplicants().add(existingUser);
//        existingUser.getJobsApplied().add(existingJob);
//
//        // Save changes
//        jobServiceImpl.add(existingJob);
//        userServiceImpl.add(existingUser);
//
//        return "redirect:/jobs-list";
//    }

}


