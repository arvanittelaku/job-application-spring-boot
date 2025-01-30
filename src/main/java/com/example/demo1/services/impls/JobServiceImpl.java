package com.example.demo1.services.impls;

import com.example.demo1.dtos.company.CompanyProfileDto;
import com.example.demo1.dtos.job.JobCreateDto;
import com.example.demo1.dtos.job.JobDeleteDto;
import com.example.demo1.dtos.job.JobSearchDto;
import com.example.demo1.dtos.job.JobUpdateDto;
import com.example.demo1.mappers.CompanyMapper;
import com.example.demo1.mappers.JobMapper;
import com.example.demo1.models.Company;
import com.example.demo1.models.Job;
import com.example.demo1.repositories.CompanyRepository;
import com.example.demo1.repositories.JobRepository;
import com.example.demo1.services.JobServices;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class JobServiceImpl implements JobServices {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;

    @Transactional
    @Override
    public void add(JobCreateDto jobCreateDto, CompanyProfileDto companyProfileDto) {
        var job = jobMapper.fromCreateToEntity(jobCreateDto);

        // Validate job fields
        if (job.getTitle() == null || job.getDescription() == null) {
            throw new IllegalArgumentException("Title and Description are required.");
        }

        // Fetch the existing company from the database
        Company company = companyRepository.findById(companyProfileDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Company not found in database."));

        // Ensure the company's job list is initialized
        if (company.getJobs() == null) {
            company.setJobs(new ArrayList<>());
        }

        // Add job to company's job list
        company.getJobs().add(job);
        job.setJobOwner(company);

        // Save both the company and job
        companyRepository.save(company);
    }


    //        List<Job> jobs = new ArrayList<>();
//        var job = jobMapper.fromCreateToEntity(jobCreateDto);
//        jobs.add(job);
//        companyProfileDto.setJobs(jobs);






    @Override
    public Job find(JobSearchDto jobSearchDto) {
        var exist = jobRepository.findByTitle(jobSearchDto.getTitle());
        if (exist.isEmpty()) {
            throw new EntityNotFoundException("Job not found");
        }
        var job = jobMapper.fromSearchToEntity(jobSearchDto);
        return job;

    }

    @Override
    public Job modify(JobUpdateDto jobUpdateDto) {
        var exist = jobRepository.findByTitle(jobUpdateDto.getTitle());
        if (exist.isEmpty()) {
            throw new EntityNotFoundException("Job not found");
        }
       var job = jobMapper.fromUpdateToEntity(jobUpdateDto);
       return jobRepository.save(job);
    }

    @Override
    public void remove(JobDeleteDto id) {
        var exist = jobRepository.findById(id.getId());
        if (exist.isEmpty()) {
            throw new EntityNotFoundException("Job not found");
        }
        var job = jobMapper.fromDeleteToEntity(id);
        jobRepository.removeJobByTitle(job.getTitle());
    }

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Job findById(long id) {
        var exist = jobRepository.findById(id);
        if (exist.isEmpty()) {
            throw new EntityNotFoundException("Job not found");
        }
        return exist.get();
    }

    public List<Job> findAllByTitleAndLocation(String title, String location) {
        List<Job> jobs = jobRepository.findAllByTitleAndLocation(title, location);
        if (jobs.isEmpty()) {
            throw new EntityNotFoundException("Job not found");
        }
        return jobs;
    }
}
