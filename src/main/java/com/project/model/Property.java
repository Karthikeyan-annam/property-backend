package com.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String location;
    private String propertyType;
    private Double budget;
    private Double area;
    
    private String propertyCondition;

    private Integer userId; // Foreign key representing the User

    // Constructors
    public Property() {
    }

    public Property(String location, String propertyType, Double budget, Double area, String propertyCondition, Integer userId) {
        this.location = location;
        this.propertyType = propertyType;
        this.budget = budget;
        this.area = area;
        this.propertyCondition = propertyCondition;
        this.userId = userId;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getPropertyCondition() {
        return propertyCondition;
    }

    public void setPropertyCondition(String propertyCondition) {
        this.propertyCondition = propertyCondition;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
