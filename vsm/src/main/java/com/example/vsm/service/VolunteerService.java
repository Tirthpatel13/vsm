package com.example.vsm.service;

import com.example.vsm.exception.VolunteerNotFoundException;
import com.example.vsm.model.Volunteer;
import com.example.vsm.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VolunteerService {
    private final VolunteerRepository repository;

    public Volunteer createVolunteer(Volunteer volunteer) {
        return repository.save(volunteer);
    }

    public Volunteer updateVolunteer(String id, Volunteer updated) {
        Volunteer existing = repository.findById(id)
                .orElseThrow(() -> new VolunteerNotFoundException("Volunteer not found with id: " + id));
        existing.setName(updated.getName());
        existing.setAge(updated.getAge());
        existing.setDepartment(updated.getDepartment());
        existing.setLocation(updated.getLocation());
        return repository.save(existing);
    }

    public void deleteVolunteer(String id) {
        if (!repository.existsById(id)) {
            throw new VolunteerNotFoundException("Cannot delete. Volunteer not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public List<Volunteer> getAllVolunteers() {
        return repository.findAll();
    }

    public Volunteer getVolunteerById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new VolunteerNotFoundException("Volunteer not found with id: " + id));
    }

    public List<Volunteer> searchVolunteers(String name, Integer age, String department, String location) {
        return repository.findAll().stream()
                .filter(v -> name == null || v.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(v -> age == null || v.getAge() == age)
                .filter(v -> department == null || v.getDepartment().toLowerCase().contains(department.toLowerCase()))
                .filter(v -> location == null || v.getLocation().toLowerCase().contains(location.toLowerCase()))
                .collect(Collectors.toList());
    }
}
