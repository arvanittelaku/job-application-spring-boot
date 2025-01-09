package com.example.demo1.exceptions;

public class EmailExistException extends RuntimeException{

    public EmailExistException(String message) {
        super(message);
    }

    public EmailExistException() {
        super("Email already exists");
    }
}
