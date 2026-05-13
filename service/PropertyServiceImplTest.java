 package com.propertymanager.service;

import com.propertymanager.dto.PropertyDto;
import com.propertymanager.entity.Property;
import com.propertymanager.entity.PropertyManager;
import com.propertymanager.exception.PropertyNotFoundException;
import com.propertymanager.repository.PropertyManagerRepository;
import com.propertymanager.repository.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PropertyServiceImplTest {

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private PropertyManagerRepository managerRepository;

    @InjectMocks
    private PropertyServiceImpl propertyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addProperty_NewManager_ShouldSaveSuccessfully() {
        PropertyDto dto = new PropertyDto();
        dto.setName("Sunset Villa");
        dto.setArea("Banjara Hills");
        dto.setRentalPrice(25000);
        dto.setOccupied(false);
        dto.setManagerName("Rahul");
        dto.setManagerPassword("pass123");

        PropertyManager manager = new PropertyManager();
        manager.setName("Rahul");

        Property savedProperty = new Property();
        savedProperty.setId(1L);
        savedProperty.setName("Sunset Villa");
        savedProperty.setManager(manager);

        when(managerRepository.findByName("Rahul")).thenReturn(Optional.empty());
        when(managerRepository.save(any())).thenReturn(manager);
        when(propertyRepository.save(any())).thenReturn(savedProperty);

        Property result = propertyService.addProperty(dto);

        assertNotNull(result);
        assertEquals("Sunset Villa", result.getName());
        verify(propertyRepository, times(1)).save(any());
    }

    @Test
    void getProperty_ExistingId_ShouldReturnProperty() {
        Property property = new Property();
        property.setId(1L);
        property.setName("Green Apartments");

        when(propertyRepository.findById(1L)).thenReturn(Optional.of(property));

        Property result = propertyService.getProperty(1L);

        assertNotNull(result);
        assertEquals("Green Apartments", result.getName());
    }

    @Test
    void getProperty_InvalidId_ShouldThrowException() {
        when(propertyRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(PropertyNotFoundException.class, () -> propertyService.getProperty(99L));
    }

    @Test
    void getAllProperties_ShouldReturnList() {
        Property p1 = new Property();
        p1.setName("Villa 1");
        Property p2 = new Property();
        p2.setName("Villa 2");

        when(propertyRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Property> result = propertyService.getAllProperties();

        assertEquals(2, result.size());
    }

    @Test
    void deleteProperty_ExistingId_ShouldDelete() {
        Property property = new Property();
        property.setId(1L);

        when(propertyRepository.findById(1L)).thenReturn(Optional.of(property));
        doNothing().when(propertyRepository).delete(property);

        assertDoesNotThrow(() -> propertyService.deleteProperty(1L));
        verify(propertyRepository, times(1)).delete(property);
    }

    @Test
    void deleteProperty_InvalidId_ShouldThrowException() {
        when(propertyRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(PropertyNotFoundException.class, () -> propertyService.deleteProperty(99L));
    }

    @Test
    void calculateManagerSalary_ShouldReturn10Percent() {
        Property p1 = new Property();
        p1.setRentalPrice(10000);
        Property p2 = new Property();
        p2.setRentalPrice(20000);

        when(propertyRepository.findByManagerName("Rahul")).thenReturn(Arrays.asList(p1, p2));

        double salary = propertyService.calculateManagerSalary("Rahul");

        assertEquals(3000.0, salary);
    }

    @Test
    void getOccupiedProperties_ShouldReturnOccupiedOnly() {
        Property p1 = new Property();
        p1.setOccupied(true);

        when(propertyRepository.findByOccupiedTrue()).thenReturn(List.of(p1));

        List<Property> result = propertyService.getOccupiedProperties();

        assertEquals(1, result.size());
        assertTrue(result.get(0).isOccupied());
    }
}