package com.example.demo1.dtos.job;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobDeleteDto {
    @NotNull(message = "Id is required")
    @NotBlank(message = "Id is required")
    private Long id;
}
