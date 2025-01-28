package com.example.demo1.dtos.company;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;

public class CompanyUpdateDto extends CompanyProfileDto {

    @Nullable
    private String companyName;

    @Nullable
    private String industry;

    @Nullable
    private String email;

    @Nullable
    private String phone;

    @Nullable
    private String address;

    @Nullable
    private String city;

    @Nullable
    private String state;

    @Nullable
    private String country;

    @Nullable
    @Pattern(
            regexp = "^(https?:\\/\\/)?([\\w\\-]+\\.)+[\\w\\-]{2,}(\\/\\S*)?$",
            message = "Invalid website URL"
    )
    private String website;

    @Nullable
    private String description;

    @Nullable
    private String location;
}
