package com.example.demo1.dtos.user;

import com.example.demo1.annotations.PasswordMatches;
import com.example.demo1.models.UserRole;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegDto {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{7,}$",
            message = "Password must contain at least one letter and one number"
    )
    private String password;

    @NotBlank(message = "Confirm Password is required")
    private String confirmPassword;
}

