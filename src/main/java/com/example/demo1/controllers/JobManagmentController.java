package com.example.demo1.controllers;

import com.example.demo1.dtos.job.JobCreateDto;
import com.example.demo1.dtos.job.JobDeleteDto;
import com.example.demo1.dtos.job.JobUpdateDto;
import com.example.demo1.services.impls.JobServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("crud")
@RequiredArgsConstructor
@Controller
public class JobManagmentController {
    private final JobServiceImpl jobService;

//    @GetMapping("/jobs")
//    public String jobs(Model model) {
//        model.addAttribute("jobs", jobService.findAll());
//        return "jobs-list";
//    }

    @GetMapping("/jobs/add")
    public String addJob(Model model) {
        model.addAttribute("jobCreateDto", new JobCreateDto());
        return "crud/jobs-add";
    }
    @PostMapping("/jobs/add")
    public String addJob(JobCreateDto jobCreateDto) {
        jobService.add(jobCreateDto);
        return "redirect:/crud/jobs";
    }

    @GetMapping("/{id}/delete")
    public String deleteJob(Model model) {
        model.addAttribute("jobDeleteDto", new JobCreateDto());
        return "crud/jobs-delete";
    }
    @PostMapping("/{id}/delete")
    public String deleteJob(@ModelAttribute JobDeleteDto jobDeleteDto) {
        jobService.remove(jobDeleteDto);
        return "redirect:/crud/jobs";
    }

    @GetMapping("/{id}/edit")
    public String editJob(Model model) {
        model.addAttribute("jobUpdateDto", new JobUpdateDto());
        return "crud/jobs-edit";
    }
    @PostMapping("/{id}/edit")
    public String editJob(@ModelAttribute JobUpdateDto jobUpdateDto) {
        jobService.modify(jobUpdateDto);
        return "redirect:/crud/jobs";
    }
    @GetMapping("/{id}/details")
    public String detailsJob(@PathVariable long id,Model model) {
        model.addAttribute("jobDetailsDto", jobService.findById(id));
        return "crud/jobs-details";
    }




}
