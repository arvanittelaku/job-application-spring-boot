package com.example.demo1.dtos.user;

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
public class UserUpdateReqDto {
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;
    @NotNull(message = "Last name is required")
    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters")
    private String lastName;
    @NotNull(message = "Username is required")
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;
    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 40, message = "Password must be between 8 and 40 characters")
    private String password;

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Email
    private String email;
    @NotNull(message = "Phone is required")
    @NotBlank(message = "Phone is required")
    @Size(min = 9, max = 20, message = "Phone must be between 9 and 20 characters")
    private String phone;
    @NotNull(message = "Address is required")
    @NotBlank(message = "Address is required")
    @Size(min = 3, max = 20, message = "Address must be between 3 and 20 characters")
    private String address;
    @NotNull(message = "City is required")
    @NotBlank(message = "City is required")
    @Size(min = 3, max = 20, message = "City must be between 3 and 20 characters")
    private String city;
    @NotNull(message = "State is required")
    @NotBlank(message = "State is required")
    @Size(min = 3, max = 20, message = "State must be between 3 and 20 characters")
    private String state;
    @NotNull(message = "Country is required")
    @NotBlank(message = "Country is required")
    @Size(min = 3, max = 20, message = "Country must be between 3 and 20 characters")
    private String country;
    @NotNull(message = "Bio is required")
    @NotBlank(message = "Bio is required")
    @Size(min = 5, max = 200, message = "Bio must be between 3 and 100 characters")
    private String bio;
    @NotNull(message = "Profile image is required")
    @NotBlank(message = "Profile image is required")
    private String profileImage;


}
