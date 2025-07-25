package com.example.vsm.service;

import com.example.vsm.model.Volunteer;
import com.example.vsm.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.vsm.exception.VolunteerNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service // Marks this as a Spring-managed service class
@RequiredArgsConstructor // Lombok: auto-generates constructor for final fields
public class VolunteerService {

    private final VolunteerRepository repository; // Repository to interact with MongoDB

    // Save a new volunteer
    public Volunteer createVolunteer(Volunteer volunteer) {
        return repository.save(volunteer);
    }

    // Update volunteer by ID
    public Volunteer updateVolunteer(String id, Volunteer updated) {
        Volunteer existing = repository.findById(id)
                .orElseThrow(() -> new VolunteerNotFoundException("Volunteer not found with id: " + id));

        // Update fields
        existing.setName(updated.getName());
        existing.setAge(updated.getAge());
        existing.setDepartment(updated.getDepartment());
        existing.setLocation(updated.getLocation());

        return repository.save(existing); // Save updated data
    }

    // Delete a volunteer by ID
    public void deleteVolunteer(String id) {
        if (!repository.existsById(id)) {
            throw new VolunteerNotFoundException("Cannot delete. Volunteer not found with id: " + id);
        }
        repository.deleteById(id);
    }

    // Fetch all volunteers
    public List<Volunteer> getAllVolunteers() {
        return repository.findAll();
    }

    // Find volunteer by ID
    public Volunteer getVolunteerById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new VolunteerNotFoundException("Volunteer not found with id: " + id));
    }

    // Search volunteers by various attributes using Java Streams
    public List<Volunteer> searchVolunteers(String name, Integer age, String department, String location) {
        return repository.findAll().stream()
                .filter(v -> name == null || v.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(v -> age == null || v.getAge() == age)
                .filter(v -> department == null || v.getDepartment().toLowerCase().contains(department.toLowerCase()))
                .filter(v -> location == null || v.getLocation().toLowerCase().contains(location.toLowerCase()))
                .collect(Collectors.toList());
    }
}
