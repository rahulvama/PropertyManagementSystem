package com.propertymanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;


@Entity
@Table(name = "property_manager")
public class PropertyManager {
    @Id
    private String name;

    private String password;

    @OneToMany(mappedBy = "manager")
    private List<Property> properties;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public List<Property> getProperties() { return properties; }
    public void setProperties(List<Property> properties) { this.properties = properties; }
}