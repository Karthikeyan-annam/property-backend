package com.project.service;

import com.project.model.Property;
import com.project.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@SuppressWarnings("null")
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public List<Property> getPropertiesByUserId(Integer userId) {
        return propertyRepository.findByUserId(userId);
    }

    public Property getPropertyById(Integer id) {
        Optional<Property> property = propertyRepository.findById(id);
        return property.orElse(null);
    }

    public Property updateProperty(Integer id, Property updatedProperty) {
        if (propertyRepository.existsById(id)) {
            updatedProperty.setId(id);
            return propertyRepository.save(updatedProperty);
        }
        return null;
    }

    public void deleteProperty(Integer id) {
        propertyRepository.deleteById(id);
    }
}
