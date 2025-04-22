package com.propertymanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import com.propertymanager.dto.PropertyDto;
import com.propertymanager.dto.PropertyUpdateRequest;
import com.propertymanager.entity.Property;
import com.propertymanager.entity.PropertyManager;
import com.propertymanager.exception.PropertyNotFoundException;
import com.propertymanager.repository.PropertyRepository;
import com.propertymanager.repository.PropertyManagerRepository;

@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyManagerRepository managerRepository;

    /**
     * Adds a new property. If the manager doesn't exist, creates a new one.
     */
    @Override
    public Property addProperty(PropertyDto dto) {
        try {
            // Fetch existing manager or create new
            PropertyManager manager = managerRepository.findById(dto.getManagerName())
                    .orElseGet(() -> {
                        PropertyManager newManager = new PropertyManager();
                        newManager.setName(dto.getManagerName());
                        newManager.setPassword(dto.getManagerPassword());
                        return managerRepository.save(newManager);
                    });

            // Create and save new property
            Property property = new Property();
            property.setAddress(dto.getAddress());
            property.setArea(dto.getArea());
            property.setOccupied(dto.isOccupied());
            property.setValue(dto.getValue());
            property.setManager(manager);

            return propertyRepository.save(property);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add property: " + e.getMessage());
        }
    }

    /**
     * Updates the value of an existing property after verifying manager credentials.
     */
    @Override
    public Property updateProperty(Long propertyId, PropertyUpdateRequest request) {
        try {
            Property property = propertyRepository.findById(propertyId)
                    .orElseThrow(() -> new PropertyNotFoundException("Property not found"));

            PropertyManager manager = property.getManager();

            // Check if credentials match
            if (!manager.getName().equals(request.getManagerName()) ||
                    !manager.getPassword().equals(request.getManagerPassword())) {
                throw new RuntimeException("Unauthorized update attempt");
            }

            property.setValue(request.getUpdatedValue());
            return propertyRepository.save(property);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update property: " + e.getMessage());
        }
    }

    /**
     * Calculates total salary for a manager: 10% of total value of occupied properties.
     */
    @Override
    public double calculateSalary(String managerName) {
        PropertyManager manager = managerRepository.findById(managerName)
                .orElseThrow(() -> new PropertyNotFoundException("Manager not found"));

        return manager.getProperties().stream()
                .filter(Property::isOccupied)
                .mapToDouble(p -> p.getValue() * 0.10)
                .sum();
    }

    /**
     * Retrieves property by ID.
     */
    @Override
    public Optional<Property> getProperty(Long id) {
        return Optional.ofNullable(propertyRepository.findById(id)
                .orElseThrow(() -> new PropertyNotFoundException("Property with ID " + id + " not found")));
    }

    /**
     * Returns all properties in the system.
     */
    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    /**
     * Returns properties in a specific area.
     */
    @Override
    public List<Property> getPropertiesByArea(String area) {
        return propertyRepository.findAll().stream()
                .filter(p -> p.getArea().equalsIgnoreCase(area))
                .collect(Collectors.toList());
    }

    /**
     * Returns all currently occupied properties.
     */
    @Override
    public List<Property> getOccupiedProperties() {
        return propertyRepository.findAll().stream()
                .filter(Property::isOccupied)
                .collect(Collectors.toList());
    }

    /**
     * Returns the property with the lowest value.
     */
    @Override
    public Property getLowestValueProperty() {
        return propertyRepository.findAll().stream()
                .min(Comparator.comparingDouble(Property::getValue))
                .orElseThrow(() -> new PropertyNotFoundException("No properties available"));
    }

    /**
     * Returns count of properties managed by a specific manager.
     */
    @Override
    public long getPropertyCountByManager(String managerName) {
        PropertyManager manager = managerRepository.findById(managerName)
                .orElseThrow(() -> new PropertyNotFoundException("Manager not found"));
        return manager.getProperties().size();
    }
}
