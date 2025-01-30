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
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String bio;

    @Column(nullable = true)
    private LocalDateTime joinDate;

    @Column(nullable = true)
    private LocalDate birthdate;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String city;

    @Column(nullable = true)
    private String state;

    @Column(nullable = true)
    private String country;

    @Column(nullable = true)
    private String postalCode;

    @Column(nullable = true, unique = true)
    private String phone;

    @Column(nullable = true)
    private String gender;

    @Column(nullable = true)
    private String profileImage;

    @Column(nullable = true)
    private String cvFileName;


    @ManyToMany
    @JoinTable(
            name = "user_job_applications",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private List<Job> jobsApplied = new ArrayList<>();;

}
