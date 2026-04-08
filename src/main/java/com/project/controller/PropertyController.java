package com.project.controller;

import com.project.model.Property;
import com.project.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "*")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<Property> addProperty(@RequestBody Property property) {
        System.out.println("POST /api/properties — incoming: " + property);
        Property savedProperty = propertyService.addProperty(property);
        System.out.println("POST /api/properties — saved id=" + savedProperty.getId());
        return new ResponseEntity<>(savedProperty, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        System.out.println("GET /api/properties — count=" + properties.size());
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Property>> getPropertiesByUser(@PathVariable Integer userId) {
        System.out.println("GET /api/properties/user/" + userId);
        List<Property> list = propertyService.getPropertiesByUserId(userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Integer id) {
        Property property = propertyService.getPropertyById(id);
        if (property != null) {
            return new ResponseEntity<>(property, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Integer id, @RequestBody Property updatedProperty) {
        Property property = propertyService.updateProperty(id, updatedProperty);
        if (property != null) {
            return new ResponseEntity<>(property, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Integer id) {
        propertyService.deleteProperty(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
