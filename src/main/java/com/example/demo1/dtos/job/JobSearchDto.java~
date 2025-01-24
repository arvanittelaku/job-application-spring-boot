package com.example.demo1.dtos.job;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobSearchDto {
    @NotNull(message = "Title is required")
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 20, message = "Title must be between 3 and 20 characters")
    private String title;
    @NotNull(message = "Location is required")
    @NotBlank(message = "Location is required")
    @Size(min = 3, max = 20, message = "Location must be between 3 and 20 characters")
    private String location;
    @NotNull(message = "Min salary is required")
    @NotBlank(message = "Min salary is required")
    @Size(min = 3, max = 20, message = "Min salary must be between 3 and 20 characters")
    private double minSalary;
    @NotNull(message = "Max salary is required")
    @NotBlank(message = "Max salary is required")
    @Size(min = 3, max = 20, message = "Max salary must be between 3 and 20 characters")
    private double maxSalary;
}
