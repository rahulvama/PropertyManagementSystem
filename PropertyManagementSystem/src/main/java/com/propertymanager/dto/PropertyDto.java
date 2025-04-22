package com.propertymanager.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class PropertyDto {
    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Area is required")
    private String area;

    private boolean occupied;

    @Min(value = 0, message = "Value must be positive")
    private double value;

    @NotBlank(message = "Manager name is required")
    private String managerName;

    @NotBlank(message = "Manager password is required")
    private String managerPassword;

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
    public boolean isOccupied() { return occupied; }
    public void setOccupied(boolean occupied) { this.occupied = occupied; }
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    public String getManagerName() { return managerName; }
    public void setManagerName(String managerName) { this.managerName = managerName; }
    public String getManagerPassword() { return managerPassword; }
    public void setManagerPassword(String managerPassword) { this.managerPassword = managerPassword; }
}