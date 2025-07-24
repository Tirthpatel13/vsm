package com.example.vsm.repository;

import com.example.vsm.model.Volunteer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VolunteerRepository extends MongoRepository<Volunteer, String> {
    List<Volunteer> findByNameContainingIgnoreCase(String name);
    List<Volunteer> findByAge(int age);
    List<Volunteer> findByDepartment(String department);
    List<Volunteer> findByLocation(String location);
}
