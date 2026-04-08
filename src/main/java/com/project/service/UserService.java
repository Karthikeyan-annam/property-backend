package com.project.service;

import com.project.model.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@SuppressWarnings("null")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Registers a new user. Call before save to avoid DB constraint errors when possible.
     */
    public User registerUser(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required.");
        }
        String email = user.getEmail().trim();
        user.setEmail(email);

        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already exists. Please login.");
        }

        return userRepository.save(user);
    }

    public User validateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Simple password validation as per requirements (no encryption)
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // or throw exception
    }
}
