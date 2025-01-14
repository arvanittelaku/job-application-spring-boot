package com.example.demo1.annotations;

import com.example.demo1.dtos.user.UserRegDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.Annotation;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserRegDto> {

    @Override
    public boolean isValid(UserRegDto dto, ConstraintValidatorContext context) {
        return dto.getPassword() != null && dto.getPassword().equals(dto.getConfirmPassword());
    }

}
