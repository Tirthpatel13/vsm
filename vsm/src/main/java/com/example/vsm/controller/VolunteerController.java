package com.example.vsm.controller;

import com.example.vsm.model.Volunteer;
import com.example.vsm.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
@RequiredArgsConstructor
@Validated
public class VolunteerController {
    private final VolunteerService service;
    // Logger instance for tracking actions and debugging
    private static final Logger logger = LoggerFactory.getLogger(VolunteerController.class);

    @PostMapping
    public ResponseEntity<Volunteer> create(@Valid @RequestBody Volunteer volunteer) {
        logger.info("Creating volunteer: {}", volunteer);
        return ResponseEntity.ok(service.createVolunteer(volunteer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Volunteer> update(@PathVariable String id, @Valid @RequestBody Volunteer volunteer) {
        logger.info("Updating volunteer with id {}: {}", id, volunteer);
        return ResponseEntity.ok(service.updateVolunteer(id, volunteer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        logger.info("Deleting volunteer with id: {}", id);
        service.deleteVolunteer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Volunteer>> getAll() {
        return ResponseEntity.ok(service.getAllVolunteers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Volunteer> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getVolunteerById(id));
    }


     // Search volunteers by name, age, department, or location
     //URL: GET /api/volunteers/search?name=John&department=AV


    @GetMapping("/search")
    public ResponseEntity<List<Volunteer>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String location) {
        return ResponseEntity.ok(service.searchVolunteers(name, age, department, location));
    }
}
