package com.example.demo1.dtos.user;

import com.example.demo1.models.UserRole;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegDto {

    //TODO: Add validation

    @PositiveOrZero
    private Long id;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String bio;
    private LocalDateTime joinDate;
    private LocalDate birthdate;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String phone;
    private String gender;
    private String profileImage;
    private UserRole role;

}
