package com.example.demo1.dtos.user;

import com.example.demo1.models.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserResponseDto {
    @NotNull(message = "Username is required")
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;
    @NotBlank(message = "Name is required")
    @NotNull(message = "Name is required")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;
    @NotNull(message = "Last name is required")
    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters")
    private String lastName;
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Email
    private String email;
    @NotNull(message = "Address is required")
    @NotBlank(message = "Address is required")
    @Size(min = 3, max = 20, message = "Address must be between 3 and 20 characters")
    private String address;
    @NotNull(message = "City is required")
    @NotBlank(message = "City is required")
    @Size(min = 3, max = 20, message = "City must be between 3 and 20 characters")
    private String city;
    @NotNull(message = "Country is required")
    @NotBlank(message = "Country is required")
    @Size(min = 3, max = 20, message = "Country must be between 3 and 20 characters")
    private String country;
    @NotNull(message = "Postal code is required")
    @NotBlank(message = "Postal code is required")
    @Size(min = 3, max = 20, message = "Postal code must be between 3 and 20 characters")
    private String postalCode;
    @NotNull(message = "Phone number is required")
    @NotBlank(message = "Phone number is required")
    @Size(min = 9, max = 20, message = "Phone number must be between 9 and 20 characters")
    private String phone;
    @NotNull(message = "Bio is required")
    @NotBlank(message = "Bio is required")
    @Size(min = 5, max = 200, message = "Bio must be between 3 and 20 characters")
    private String bio;

    private String profileImage;
    @NotNull(message = "Role is required")
    @NotBlank(message = "Role is required")
    private UserRole role;


}
