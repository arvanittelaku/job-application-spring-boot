package com.example.demo1.dtos.company;

import jakarta.validation.constraints.*;

public class CompanyUpdateDto extends CompanyProfileDto {

    private String companyName;

    private String industry;

    private String email;

    private String phone;

    private String address;

    private String city;

    private String state;

    private String country;

    @Pattern(
            regexp = "^(https?:\\/\\/)?([\\w\\-]+\\.)+[\\w\\-]{2,}(\\/\\S*)?$",
            message = "Invalid website URL"
    )
    private String website;

    private String description;

    private String location;
}
