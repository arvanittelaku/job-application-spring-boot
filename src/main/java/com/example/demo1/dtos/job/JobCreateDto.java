package com.example.demo1.dtos.job;

import com.example.demo1.dtos.company.CompanyProfile;
import com.example.demo1.models.JobCategory;
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
public class JobCreateDto extends CompanyProfile {

    @NotNull(message = "Title is required")
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 20, message = "Title must be between 3 and 20 characters")
    private String title;

    @NotNull(message = "Description is required")
    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 70, message = "Description must be between 10 and 70 characters")
    private String description;

    @NotNull(message = "Location is required")
    @NotBlank(message = "Location is required")
    @Size(min = 3, max = 20, message = "Location must be between 3 and 20 characters")
    private String location;

    @NotNull(message = "Salary is required")
    private double salary;

    @NotNull(message = "Category is required")
    private JobCategory category;

    @NotNull(message = "Deadline is required")
    private LocalDateTime deadline;

    @NotNull(message = "Requirements are required")
    @NotBlank(message = "Requirements are required")
    @Size(min = 8, max = 200, message = "Requirements must be between 8 and 200 characters")
    private String requirements;
}
