package com.example.vsm.exception;

public class VolunteerNotFoundException extends RuntimeException {
    public VolunteerNotFoundException(String message) {
        super(message);// Calls the parent
    }
}

