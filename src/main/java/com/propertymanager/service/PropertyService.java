package com.propertymanager.service;

import com.propertymanager.dto.PropertyDto;
import com.propertymanager.entity.Property;
import java.util.List;

public interface PropertyService {
    Property addProperty(PropertyDto dto);
    Property updateProperty(Long id, PropertyDto dto);
    void deleteProperty(Long id);
    Property getProperty(Long id);
    List<Property> getAllProperties();
    List<Property> getPropertiesByArea(String area);
    List<Property> getOccupiedProperties();
    List<Property> getPropertiesByManager(String managerName);
    double calculateManagerSalary(String managerName);
}