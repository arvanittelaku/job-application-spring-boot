package com.example.demo1.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(nullable = true)
    private UserRole role;

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

    private String profileImage;
}
