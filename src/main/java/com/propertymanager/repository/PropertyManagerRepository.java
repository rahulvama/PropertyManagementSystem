package com.propertymanager.repository;

import com.propertymanager.entity.PropertyManager;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PropertyManagerRepository extends JpaRepository<PropertyManager, Long> {
    Optional<PropertyManager> findByName(String name);
}