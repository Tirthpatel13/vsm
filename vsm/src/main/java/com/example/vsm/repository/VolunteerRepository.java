package com.example.vsm.repository;

import com.example.vsm.model.Volunteer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends MongoRepository<Volunteer, String> {
    List<Volunteer> findByNameContainingIgnoreCase(String name);
    List<Volunteer> findByAge(int age);
    List<Volunteer> findByDepartmentContainingIgnoreCase(String department);
    List<Volunteer> findByLocationContainingIgnoreCase(String location);
}