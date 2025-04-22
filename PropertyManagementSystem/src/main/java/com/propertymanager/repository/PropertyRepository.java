package com.propertymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.propertymanager.entity.Property;
import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    // Custom queries for better performance
    List<Property> findByAreaIgnoreCase(String area);

    List<Property> findByOccupiedTrue();

    Property findTopByOrderByValueAsc();
}
