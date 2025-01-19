package com.example.demo1.dtos.job;

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
public class JobListDto extends CompanyDto {

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

    @NotNull(message = "Deadline is required")
    private LocalDateTime deadline;
}
