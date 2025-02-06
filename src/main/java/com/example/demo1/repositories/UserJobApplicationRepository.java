package com.example.demo1.repositories;

import com.example.demo1.models.Job;
import com.example.demo1.models.UserJobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJobApplicationRepository extends JpaRepository<UserJobApplication, Long> {

    List<UserJobApplication> findByJob(Job job);
}
