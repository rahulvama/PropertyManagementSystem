package com.propertymanager.controller;

import com.propertymanager.dto.PropertyDto;
import com.propertymanager.entity.Property;
import com.propertymanager.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "*")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<Property> addProperty(@RequestBody PropertyDto dto) {
        return ResponseEntity.ok(propertyService.addProperty(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody PropertyDto dto) {
        return ResponseEntity.ok(propertyService.updateProperty(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.ok("Property deleted successfully");
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
    public ResponseEntity<List<Property>> getByArea(@PathVariable String area) {
        return ResponseEntity.ok(propertyService.getPropertiesByArea(area));
    }

    @GetMapping("/occupied")
    public ResponseEntity<List<Property>> getOccupied() {
        return ResponseEntity.ok(propertyService.getOccupiedProperties());
    }

    @GetMapping("/manager/{name}")
    public ResponseEntity<List<Property>> getByManager(@PathVariable String name) {
        return ResponseEntity.ok(propertyService.getPropertiesByManager(name));
    }

    @GetMapping("/manager/{name}/salary")
    public ResponseEntity<Double> getSalary(@PathVariable String name) {
        return ResponseEntity.ok(propertyService.calculateManagerSalary(name));
    }
}