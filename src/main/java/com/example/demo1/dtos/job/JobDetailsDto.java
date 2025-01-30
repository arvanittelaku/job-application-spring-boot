package com.example.demo1.dtos.job;

import com.example.demo1.dtos.company.CompanyProfileDto;
import com.example.demo1.models.JobCategory;
import com.example.demo1.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobDetailsDto extends CompanyProfileDto {
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

    @NotNull(message = "Requirements are required")
    @NotBlank(message = "Requirements are required")
    @Size(min = 10, max = 70, message = "Requirements must be between 10 and 70 characters")
    private String requirements;

    @NotNull(message = "Category is required")
    private JobCategory category;

    @NotNull(message = "Deadline is required")
    private LocalDateTime deadline;

    @NotNull(message = "Applicants count is required")
    private List<User> applicants;
}
