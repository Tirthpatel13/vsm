package com.example.vsm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // This marks the class as a global exception handler for all REST controllers
public class GlobalExceptionHandler {


     // Handles custom VolunteerNotFoundException

    @ExceptionHandler(VolunteerNotFoundException.class)
    public ResponseEntity<?> handleNotFound(VolunteerNotFoundException ex) {
        // Returns a 404 response with a message in JSON format: { "error": "Volunteer not found with id: X" }
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.NOT_FOUND);
    }


     // Handles validation errors when @Valid fails in request body

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        // Create a map to store field-specific error messages
        Map<String, String> errors = new HashMap<>();

        // Loop over each validation error and add it to the map
        ex.getBindingResult().getFieldErrors().forEach(err ->
                errors.put(err.getField(), err.getDefaultMessage())
        );

        // Return 400 Bad Request with the map of field errors
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
