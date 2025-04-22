package com.propertymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.propertymanager.entity.PropertyManager;

public interface PropertyManagerRepository extends JpaRepository<PropertyManager, String> {
}