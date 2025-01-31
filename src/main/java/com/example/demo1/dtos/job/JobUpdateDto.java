package com.example.demo1.dtos.job;

import com.example.demo1.dtos.company.CompanyProfileDto;
import com.example.demo1.models.JobCategory;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobUpdateDto{

    @NotNull(message = "Title is required")
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String title;

    @NotNull(message = "Description is required")
    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 200, message = "Description must be between 10 and 200 characters")
    private String description;

    @NotNull(message = "Location is required")
    @NotBlank(message = "Location is required")
    @Size(min = 3, max = 50, message = "Location must be between 3 and 50 characters")
    private String location;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be a positive number")
    private double salary;

    @NotNull(message = "Requirements are required")
    @NotBlank(message = "Requirements are required")
    @Size(min = 10, max = 200, message = "Requirements must be between 10 and 200 characters")
    private String requirements;

    @NotNull(message = "Responsibilities are required")
    @NotBlank(message = "Responsibilities are required")
    @Size(min = 10, max = 200, message = "Responsibilities must be between 10 and 200 characters")
    private String responsibilities;

    @NotNull(message = "Category is required")
    private JobCategory category;

    @NotNull(message = "Contact information is required")
    @NotBlank(message = "Contact information is required")
    private String contactInfo;

    @Id
    private Long id;
}
