package com.example.demo1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Uncomment and modify as needed
     @ManyToOne
     @JoinColumn(name = "companyName", referencedColumnName = "companyName", nullable = false)
     private Company jobOwner;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private double salary;

    @Column(nullable = false)
    private String requirements; // Qualifications, skills, etc.

    @Column(nullable = false)
    private String responsibilities; // Responsibilities or duties for the job

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobCategory category;

    @Column(nullable = false)
    private LocalDate deadline; // Application deadline

    @Column(nullable = false)
    private LocalDate createdAt = LocalDate.now(); // When the job was posted

    @Column(nullable = false)
    private String contactInfo; // Contact information or apply link

    @Column
    @ManyToMany(mappedBy = "jobsApplied", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> applicants = new ArrayList<>();



}
