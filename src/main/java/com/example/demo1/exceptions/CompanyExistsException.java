package com.example.demo1.exceptions;

public class CompanyExistsException extends RuntimeException {

    public CompanyExistsException(String message) {
        super(message);
    }
}
