package com.propertymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.propertymanager.entity.PropertyManager;
import java.util.Optional;
public interface PropertyManagerRepository extends JpaRepository<PropertyManager, Long> {
    Optional<PropertyManager> findByName(String name);
}
