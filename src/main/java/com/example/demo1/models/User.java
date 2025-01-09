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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private UserRole role;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String bio;
    @Column(nullable = false)
    private String confirmPassword;
    @Column(nullable = false)
    private LocalDateTime joinDate;
    @Column(nullable = false)
    private LocalDate birthdate;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String postalCode;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(nullable = false)
    private String gender;

    private String profileImage;
}
