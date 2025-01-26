package com.example.demo1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long company_id;

    @Column(nullable = false, unique = true)
    private String companyName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;


    @Column(nullable = false)
    private JobCategory industries;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDate founded_year;

    @Column(nullable = false)
    @OneToMany(mappedBy = "jobOwner",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Job> jobs = new ArrayList<>();

    @Column(nullable = false)
    private String website;

    @Column(nullable = false)
    private String logo;



}
