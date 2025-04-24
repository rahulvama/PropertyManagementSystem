package com.propertymanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private String area;
    private boolean isOccupied;
    private double value;

    @ManyToOne
    @JoinColumn(name = "manager_name")
    private PropertyManager manager;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
    public boolean isOccupied() { return isOccupied; }
    public void setOccupied(boolean occupied) { isOccupied = occupied; }
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    public PropertyManager getManager() { return manager; }
    public void setManager(PropertyManager manager) { this.manager = manager; }
}
