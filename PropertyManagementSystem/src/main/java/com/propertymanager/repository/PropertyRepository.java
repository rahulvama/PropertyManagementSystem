package com.propertymanager.repository;

import com.propertymanager.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByAreaIgnoreCase(String area);
    List<Property> findByOccupiedTrue();
    List<Property> findByManagerName(String managerName);
}