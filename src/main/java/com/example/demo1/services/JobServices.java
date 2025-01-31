package com.example.demo1.services;

import com.example.demo1.dtos.company.CompanyProfileDto;
import com.example.demo1.dtos.job.JobCreateDto;
import com.example.demo1.dtos.job.JobDeleteDto;
import com.example.demo1.dtos.job.JobSearchDto;
import com.example.demo1.dtos.job.JobUpdateDto;
import com.example.demo1.models.Company;
import com.example.demo1.models.Job;
import com.example.demo1.services.baseService.*;

import java.util.List;

public interface JobServices extends Findable<JobSearchDto, Job>, Removable<Long>
        , Modifiable<JobUpdateDto,Job>, FindAll<Job> {
    void add(JobCreateDto jobCreateDto, CompanyProfileDto companyProfileDto);

    Job saveJob(Job job);


    List<Job> findByCompany(Company company);

}
