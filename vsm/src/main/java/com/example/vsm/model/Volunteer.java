package com.example.vsm.model;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "volunteers")
public class Volunteer {

    @Id
    private String id;

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 18, message = "Minimum age is 18")
    private int age;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Location is required")
    private String location;

    // Constructors
    public Volunteer() {}

    public Volunteer(String id, String name, int age, String department, String location) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.location = location;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
