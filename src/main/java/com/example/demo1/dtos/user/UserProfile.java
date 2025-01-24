package com.example.demo1.dtos.user;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;
    @NotNull(message = "Last name is required")
    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters")
    private String lastName;
//    @NotNull(message = "Username is required")
//    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Email
    private String email;
    @NotNull(message = "Phone is required")
    @NotBlank(message = "Phone is required")
    @Size(min = 9, max = 20, message = "Phone must be between 9 and 20 characters")
    private String phone;
    @NotNull(message = "Bio is required")
    @NotBlank(message = "Bio is required")
    @Size(min = 5, max = 200, message = "Bio must be between 3 and 20 characters")
    private String bio;

    private String city;

    private String country;

    private String address;

}
