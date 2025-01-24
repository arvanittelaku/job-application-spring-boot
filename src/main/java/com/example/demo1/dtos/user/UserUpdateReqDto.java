package com.example.demo1.dtos.user;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateReqDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;

    @Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^[+]?\\d{9,20}$", message = "Phone number must be valid and between 9 to 20 digits")
    private String phone;

    @Size(min = 5, max = 200, message = "Bio must be between 5 and 200 characters")
    private String bio;

    @Size(min = 3, max = 50, message = "Address must be between 3 and 50 characters")
    private String address;

    @Size(min = 3, max = 50, message = "City must be between 3 and 50 characters")
    private String city;

    @Size(min = 3, max = 50, message = "State must be between 3 and 50 characters")
    private String state;

    @Size(min = 3, max = 50, message = "Country must be between 3 and 50 characters")
    private String country;

    @Pattern(regexp = "\\d{4,10}", message = "Postal Code must be a number between 4 and 10 digits")
    private String postalCode;
}
