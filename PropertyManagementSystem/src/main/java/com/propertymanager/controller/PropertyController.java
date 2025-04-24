package com.propertymanager.controller;

import org.springframework.validation.annotation.Validated;
import com.propertymanager.dto.PropertyDto;
import com.propertymanager.dto.PropertyUpdateRequest;
import com.propertymanager.entity.Property;
import com.propertymanager.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService service;

    // Adds a new property with its manager (creates manager if not exists)
    @PostMapping
    public ResponseEntity<Property> addProperty(@Valid @RequestBody PropertyDto dto) {
        return new ResponseEntity<>(service.addProperty(dto), HttpStatus.CREATED);
    }

    // Updates the value of a property after verifying manager credentials
    @PutMapping("/{id}/update")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @Valid @RequestBody PropertyUpdateRequest request) {
        return new ResponseEntity<>(service.updateProperty(id, request), HttpStatus.OK);
    }

    // Calculates manager's salary based on 10% of total value of occupied properties
    @GetMapping("/manager/{name}/salary")
    public ResponseEntity<Double> getSalary(@PathVariable String name) {
        return new ResponseEntity<>(service.calculateSalary(name), HttpStatus.OK);
    }

    // Returns property details by ID
    @GetMapping("/{id}")
    public ResponseEntity<Property> getProperty(@PathVariable Long id) {
        return new ResponseEntity<>(service.getProperty(id).get(), HttpStatus.OK);
    }

    // Returns list of all properties
    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        return new ResponseEntity<>(service.getAllProperties(), HttpStatus.OK);
    }

    // Returns properties that match the given area
    @GetMapping("/area/{area}")
    public ResponseEntity<List<Property>> getByArea(@PathVariable String area) {
        return new ResponseEntity<>(service.getPropertiesByArea(area), HttpStatus.OK);
    }

    // Returns all properties that are currently occupied
    @GetMapping("/occupied")
    public ResponseEntity<List<Property>> getOccupiedProperties() {
        return new ResponseEntity<>(service.getOccupiedProperties(), HttpStatus.OK);
    }

    // Returns the property with the lowest value
    @GetMapping("/lowest-value")
    public ResponseEntity<Property> getLowestValueProperty() {
        return new ResponseEntity<>(service.getLowestValueProperty(), HttpStatus.OK);
    }

    // Returns the number of properties managed by a specific manager
    @GetMapping("/manager/{name}/count")
    public ResponseEntity<Long> getPropertyCountByManager(@PathVariable String name) {
        return new ResponseEntity<>(service.getPropertyCountByManager(name), HttpStatus.OK);
    }
}
@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<Property> addProperty(@Valid @RequestBody PropertyDto dto) {
        return new ResponseEntity<>(propertyService.addProperty(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id,
                                                   @Valid @RequestBody PropertyUpdateRequest request) {
        return ResponseEntity.ok(propertyService.updateProperty(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getProperty(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getProperty(id));
    }

    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }

    @GetMapping("/area/{area}")
    public ResponseEntity<List<Property>> getPropertiesByArea(@PathVariable String area) {
        return ResponseEntity.ok(propertyService.getPropertiesByArea(area));
    }

    @GetMapping("/occupied")
    public ResponseEntity<List<Property>> getOccupiedProperties() {
        return ResponseEntity.ok(propertyService.getOccupiedProperties());
    }

    @GetMapping("/lowest-value")
    public ResponseEntity<Property> getLowestValueProperty() {
        return ResponseEntity.ok(propertyService.getLowestValueProperty());
    }

    @GetMapping("/salary/{managerName}")
    public ResponseEntity<Double> calculateSalary(@PathVariable String managerName) {
        return ResponseEntity.ok(propertyService.calculateSalary(managerName));
    }

    @GetMapping("/manager/{managerName}/count")
    public ResponseEntity<Long> countPropertiesByManager(@PathVariable String managerName) {
        return ResponseEntity.ok(propertyService.getPropertyCountByManager(managerName));
    }
}
