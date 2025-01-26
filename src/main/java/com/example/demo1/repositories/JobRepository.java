package com.example.demo1.repositories;

import com.example.demo1.models.Job;
import com.example.demo1.models.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findAll();
    void removeJobByTitle(String title);
    Job findById(int id);
//    List<Job> findByJobOwner(String jobOwner);
    List<Job> findByTitle(String title);
    List<Job> findByLocation(String location);
    List<Job> findBySalary(double salary);
    List<Job> findByRequirements(String requirements);
    List<Job> findByCategory(JobCategory category);
    List<Job> findByDeadline(LocalDateTime deadline);
    List<Job> findByCreatedAt(LocalDateTime createdAt);
//    List<Job> findByApplicants(int applicants);
    List<Job> findAllByTitleAndLocation(String location, String title);

}
