package com.example.demo1.services.impls;

import com.example.demo1.dtos.company.CompanyProfileDto;
import com.example.demo1.dtos.job.JobCreateDto;
import com.example.demo1.dtos.job.JobDeleteDto;
import com.example.demo1.dtos.job.JobSearchDto;
import com.example.demo1.dtos.job.JobUpdateDto;
import com.example.demo1.mappers.CompanyMapper;
import com.example.demo1.mappers.JobMapper;
import com.example.demo1.models.ApplicationStatus;
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
        // Map DTO to Job entity
        var job = jobMapper.fromCreateToEntity(jobCreateDto);

        // Validate job fields
        if (job.getTitle() == null || job.getDescription() == null) {
            throw new IllegalArgumentException("Title and Description are required.");
        }

        // Fetch the company from the database
        Company company = companyRepository.findById(companyProfileDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Company not found in the database."));

        // Set the company (and consequently the company name) in the job entity
        job.setJobOwner(company);

        // Save the job
        jobRepository.save(job);
    }



    @Override
    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

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
    @Transactional
    public Job modify(JobUpdateDto jobUpdateDto) {
        Job existingJob = jobRepository.findById(jobUpdateDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));

        // Update only fields from DTO (excluding ID & non-editable fields)
        existingJob.setTitle(jobUpdateDto.getTitle());
        existingJob.setDescription(jobUpdateDto.getDescription());
        existingJob.setLocation(jobUpdateDto.getLocation());
        existingJob.setSalary(jobUpdateDto.getSalary());
        existingJob.setRequirements(jobUpdateDto.getRequirements());
        existingJob.setResponsibilities(jobUpdateDto.getResponsibilities());
        existingJob.setCategory(jobUpdateDto.getCategory());
        existingJob.setContactInfo(jobUpdateDto.getContactInfo());

        return jobRepository.save(existingJob); // Persist changes
    }

    @Override
    public void remove(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new EntityNotFoundException("Job not found");
        }
        jobRepository.deleteById(id); // Use Spring Data JPA's built-in method
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

    public List<Job> findByCompany(Company company) {
        return jobRepository.findByJobOwner(company);
    }

    public void updateJobStatus(Long jobId, ApplicationStatus newStatus) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setStatus(newStatus);
        jobRepository.save(job);
    }

}
