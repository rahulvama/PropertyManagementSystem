package com.propertymanager.service;

import com.propertymanager.dto.PropertyDto;
import com.propertymanager.entity.Property;
import com.propertymanager.entity.PropertyManager;
import com.propertymanager.exception.PropertyNotFoundException;
import com.propertymanager.repository.PropertyManagerRepository;
import com.propertymanager.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyManagerRepository managerRepository;

    @Override
    public Property addProperty(PropertyDto dto) {
        PropertyManager manager = managerRepository.findByName(dto.getManagerName())
                .orElseGet(() -> {
                    PropertyManager newManager = new PropertyManager();
                    newManager.setName(dto.getManagerName());
                    newManager.setPassword(dto.getManagerPassword());
                    return managerRepository.save(newManager);
                });

        Property property = new Property();
        property.setName(dto.getName());
        property.setArea(dto.getArea());
        property.setRentalPrice(dto.getRentalPrice());
        property.setOccupied(dto.isOccupied());
        property.setManager(manager);

        return propertyRepository.save(property);
    }

    @Override
    public Property updateProperty(Long id, PropertyDto dto) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new PropertyNotFoundException("Property not found with id: " + id));

        property.setName(dto.getName());
        property.setArea(dto.getArea());
        property.setRentalPrice(dto.getRentalPrice());
        property.setOccupied(dto.isOccupied());

        return propertyRepository.save(property);
    }

    @Override
    public void deleteProperty(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new PropertyNotFoundException("Property not found with id: " + id));
        propertyRepository.delete(property);
    }

    @Override
    public Property getProperty(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new PropertyNotFoundException("Property not found with id: " + id));
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
    public List<Property> getPropertiesByManager(String managerName) {
        return propertyRepository.findByManagerName(managerName);
    }

    @Override
    public double calculateManagerSalary(String managerName) {
        List<Property> properties = propertyRepository.findByManagerName(managerName);
        return properties.stream()
                .mapToDouble(Property::getRentalPrice)
                .sum() * 0.10;
    }
}