package com.example.demo1.dtos.company;

import com.example.demo1.models.Job;
import com.example.demo1.models.Role;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyProfileDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private List<Job> jobs = new ArrayList<>();

    private Role role = Role.COMPANY;

    @NotNull(message = "Company name is required")
    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotNull(message = "Industry is required")
    @NotBlank(message = "Industry is required")
    private String industry;

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Email
    private String email;

    private String bio;

    @NotNull(message = "Phone is required")
    @NotBlank(message = "Phone is required")
    @Size(min = 9, max = 20, message = "Phone must be between 9 and 20 characters")
    @Pattern(
            regexp = "^\\+?[0-9. ()-]{7,25}$",
            message = "Invalid phone number"
    )
    private String phone;

    @NotNull(message = "Address is required")
    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "City is required")
    @NotBlank(message = "City is required")
    private String city;

    private String state;

    @NotNull(message = "Country is required")
    @NotBlank(message = "Country is required")
    private String country;

    @Pattern(
            regexp = "^(https?:\\/\\/)?([\\w\\-]+\\.)+[\\w\\-]{2,}(\\/\\S*)?$",
            message = "Invalid website URL"
    )
    private String website;

    private String description;

    @NotNull(message = "Location is required")
    @NotBlank(message = "Location is required")
    private String location;

    private String postalCode;
}
