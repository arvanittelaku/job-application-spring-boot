package com.example.demo1.services.impls;

import com.example.demo1.dtos.job.JobCreateDto;
import com.example.demo1.dtos.job.JobDeleteDto;
import com.example.demo1.dtos.job.JobSearchDto;
import com.example.demo1.dtos.job.JobUpdateDto;
import com.example.demo1.mappers.JobMapper;
import com.example.demo1.models.Job;
import com.example.demo1.repositories.JobRepository;
import com.example.demo1.services.JobServices;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class JobServiceImpl implements JobServices {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    @Override
    public Job add(JobCreateDto jobCreateDto) {
        var job = jobMapper.fromCreateToEntity(jobCreateDto);

        // Set creation timestamp


        // Add additional validations or settings if necessary
        if (job.getTitle() == null || job.getDescription() == null) {
            throw new IllegalArgumentException("Title and Description are required.");
        }

        // Save to repository
        try {
            return jobRepository.save(job);
        } catch (Exception e) {
            throw new RuntimeException("Error while saving the job: " + e.getMessage());
        }
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
