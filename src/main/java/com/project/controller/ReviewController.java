package com.project.controller;

import com.project.model.Property;
import com.project.model.Review;
import com.project.service.PropertyService;
import com.project.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody Review review) {
        System.out.println("POST /api/reviews — received: " + review);

        if (review.getPropertyId() == null) {
            return ResponseEntity.badRequest().body("propertyId is required.");
        }

        Property existing = propertyService.getPropertyById(review.getPropertyId());
        if (existing == null) {
            System.out.println("Reject review: property id " + review.getPropertyId() + " does not exist.");
            return ResponseEntity.badRequest().body("Property not found. Enter a valid property ID that exists in the database.");
        }

        if (review.getUsername() == null || review.getUsername().isBlank()) {
            return ResponseEntity.badRequest().body("Username is required.");
        }
        if (review.getMessage() == null || review.getMessage().isBlank()) {
            return ResponseEntity.badRequest().body("Message is required.");
        }
        if (review.getRating() == null || review.getRating() < 1 || review.getRating() > 5) {
            return ResponseEntity.badRequest().body("Rating must be between 1 and 5.");
        }

        review.setUsername(review.getUsername().trim());
        Review saved = reviewService.addReview(review);
        System.out.println("POST /api/reviews — saved id=" + saved.getId());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        System.out.println("GET /api/reviews — count=" + reviews.size());
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<Review>> getReviewsByProperty(@PathVariable Integer propertyId) {
        System.out.println("GET /api/reviews/property/" + propertyId);
        List<Review> reviews = reviewService.getReviewsByPropertyId(propertyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
