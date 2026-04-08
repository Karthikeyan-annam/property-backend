package com.project.controller;

import com.project.model.User;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        System.out.println("Incoming user data: " + user);
        try {
            User savedUser = userService.registerUser(user);
            System.out.println("Signup saved user id=" + savedUser.getId());
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Email already exists. Please login.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Could not register. Please try again.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginDto) {
        User validUser = userService.validateUser(loginDto.getEmail(), loginDto.getPassword());
        if (validUser != null) {
            return new ResponseEntity<>(validUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }
}
