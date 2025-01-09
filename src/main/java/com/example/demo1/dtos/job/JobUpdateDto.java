package com.example.demo1.dtos.job;

import com.example.demo1.models.JobCategory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobUpdateDto {
    @NotNull(message = "Job owner is required")
    @NotBlank(message = "Job owner is required")
    @Size(min = 3, max = 20, message = "Job owner must be between 3 and 20 characters")
    private String jobOwner;
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Email
    private String email;
    @NotNull(message = "Title is required")
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 20, message = "Title must be between 3 and 20 characters")
    private String title;
    @NotNull(message = "Description is required")
    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 70, message = "Description must be between 3 and 20 characters")
    private String description;
    @NotNull(message = "Location is required")
    @NotBlank(message = "Location is required")
    @Size(min = 3, max = 20, message = "Location must be between 3 and 20 characters")
    private String location;
    @NotNull(message = "Salary is required")
    @NotBlank(message = "Salary is required")
    @Size(min = 3, max = 20, message = "Salary must be between 3 and 20 characters")
    private double salary;
    @NotNull(message = "Requirements is required")
    @NotBlank(message = "Requirements is required")
    @Size(min = 10, max = 70, message = "Requirements must be between 3 and 20 characters")
    private String requirements;
    @NotNull(message = "Category is required")
    @NotBlank(message = "Category is required")
    @Size(min = 2, max = 20, message = "Category must be between 3 and 20 characters")
    private JobCategory category;
    @NotNull(message = "Deadline is required")
    private LocalDateTime deadline;
}
