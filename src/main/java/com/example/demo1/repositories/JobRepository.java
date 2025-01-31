package com.example.demo1.repositories;

import com.example.demo1.models.Company;
import com.example.demo1.models.Job;
import com.example.demo1.models.JobCategory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findAll();
    void removeJobByTitle(String title);
    @Modifying
    @Transactional
    @Query("DELETE FROM Job j WHERE j.id = :id")
    void removeById(@Param("id") Long id);

    Optional<Job> findById(Long id);
//    List<Job> findByJobOwner(String jobOwner);
    List<Job> findByTitle(String title);
    List<Job> findByLocation(String location);
    List<Job> findBySalary(double salary);
    List<Job> findByRequirements(String requirements);
    List<Job> findByCategory(JobCategory category);
    List<Job> findByDeadline(LocalDateTime deadline);
    List<Job> findByCreatedAt(LocalDateTime createdAt);
    List<Job> findByJobOwner(Company jobOwner);
    List<Job> findAllByTitleAndLocation(String location, String title);

}
