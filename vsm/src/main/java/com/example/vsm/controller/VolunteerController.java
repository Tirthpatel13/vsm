package com.example.vsm.controller;

import com.example.vsm.model.Volunteer;
import com.example.vsm.repository.VolunteerRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController {

    private final VolunteerRepository repository;

    public VolunteerController(VolunteerRepository repository) {
        this.repository = repository;
    }

    // Create
    @PostMapping
    public Volunteer addVolunteer(@Valid @RequestBody Volunteer volunteer) {
        return repository.save(volunteer);
    }

    // Read All
    @GetMapping
    public List<Volunteer> getAllVolunteers() {
        return repository.findAll();
    }

    // Read by ID
    @GetMapping("/{id}")
    public Optional<Volunteer> getVolunteerById(@PathVariable String id) {
        return repository.findById(id);
    }

    // Update
    @PutMapping("/{id}")
    public Volunteer updateVolunteer(@PathVariable String id, @Valid @RequestBody Volunteer volunteer) {
        volunteer.setId(id); // Ensure it overwrites existing
        return repository.save(volunteer);
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deleteVolunteer(@PathVariable String id) {
        repository.deleteById(id);
    }

    // Search
    @GetMapping("/search")
    public List<Volunteer> searchVolunteers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String location
    ) {
        if (name != null) return repository.findByNameContainingIgnoreCase(name);
        if (age != null) return repository.findByAge(age);
        if (department != null) return repository.findByDepartment(department);
        if (location != null) return repository.findByLocation(location);
        return repository.findAll(); // default fallback
    }
}
