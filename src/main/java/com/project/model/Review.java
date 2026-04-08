package com.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String message;
    private Integer rating; // 1-5

    @Column(name = "property_id")
    private Integer propertyId; // Foreign key representing the Property

    // Constructors
    public Review() {
    }

    public Review(String username, String message, Integer rating, Integer propertyId) {
        this.username = username;
        this.message = message;
        this.rating = rating;
        this.propertyId = propertyId;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", message='" + message + '\'' +
                ", rating=" + rating +
                ", propertyId=" + propertyId +
                '}';
    }
}
