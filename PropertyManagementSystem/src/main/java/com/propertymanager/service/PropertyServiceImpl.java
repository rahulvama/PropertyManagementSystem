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

    @Override
    public Property addProperty(PropertyDto dto) {
        PropertyManager manager = managerRepository.findByName(dto.getManagerName()).orElseGet(() -> {
            PropertyManager newManager = new PropertyManager();
            newManager.setName(dto.getManagerName());
            newManager.setPassword(dto.getManagerPassword());
            managerRepository.save(newManager);
            return newManager;
        });

        Property property = new Property();
        property.setName(dto.getName());
        property.setArea(dto.getArea());
        property.setRentalPrice(dto.getRentalPrice());
        property.setCurrentValue(dto.getCurrentValue());
        property.setOccupied(dto.isOccupied());
        property.setManager(manager);

        return propertyRepository.save(property);
    }

    @Override
    public Property updateProperty(Long id, PropertyUpdateRequest request) {
        Property property = propertyRepository.findById(id).orElseThrow(() ->
                new PropertyNotFoundException("Property not found"));

        PropertyManager manager = managerRepository.findByName(request.getManagerName()).orElseThrow(() ->
                new PropertyOperationException("Manager not found or unauthorized", null));

        if (!manager.getPassword().equals(request.getManagerPassword())) {
            throw new PropertyOperationException("Invalid credentials", null);
        }

        if (!property.getManager().getName().equals(manager.getName())) {
            throw new PropertyOperationException("Manager cannot update this property", null);
        }

        property.setOccupied(request.isOccupied());
        property.setRentalPrice(request.getRentalPrice());
        property.setCurrentValue(request.getCurrentValue());

        return propertyRepository.save(property);
    }

    @Override
    public double calculateSalary(String managerName) {
        PropertyManager manager = managerRepository.findByName(managerName)
                .orElseThrow(() -> new PropertyOperationException("Manager not found", null));

        return manager.getProperties().stream()
                .mapToDouble(Property::getRentalPrice)
                .sum() * 0.10;
    }

    @Override
    public Property getProperty(Long id) {
        return propertyRepository.findById(id).orElseThrow(() ->
                new PropertyNotFoundException("Property not found"));
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public List<Property> getPropertiesByArea(String area) {
        return propertyRepository.findByAreaIgnoreCase(area);
    }

    @Override
    public List<Property> getOccupiedProperties() {
        return propertyRepository.findByOccupiedTrue();
    }

    @Override
    public Property getLowestValueProperty() {
        return propertyRepository.findTopByOrderByCurrentValueAsc();
    }

    @Override
    public long getPropertyCountByManager(String managerName) {
        return managerRepository.findByName(managerName)
                .map(m -> (long) m.getProperties().size())
                .orElse(0L);
    }
}
