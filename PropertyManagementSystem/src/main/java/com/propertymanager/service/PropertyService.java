package com.propertymanager.service;

import com.propertymanager.dto.PropertyDto;
import com.propertymanager.dto.PropertyUpdateRequest;
import com.propertymanager.entity.Property;

import java.util.List;
import java.util.Optional;


public interface PropertyService {
    Property addProperty(PropertyDto dto);
    Property updateProperty(Long id, PropertyUpdateRequest request);
    double calculateSalary(String managerName);
    Property getProperty(Long id);
    List<Property> getAllProperties();
    List<Property> getPropertiesByArea(String area);
    List<Property> getOccupiedProperties();
    Property getLowestValueProperty();
    long getPropertyCountByManager(String managerName);
}