package com.propertymanager.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class PropertyUpdateRequest {
    @NotBlank(message = "Manager name is required")
    private String managerName;

    @NotBlank(message = "Manager password is required")
    private String managerPassword;

    private boolean occupied;

    @Min(value = 0, message = "Rental price must be non-negative")
    private double rentalPrice;

    @Min(value = 0, message = "Current value must be non-negative")
    private double currentValue;

    // Getters/setters
    public String getManagerName() { return managerName; }
    public void setManagerName(String managerName) { this.managerName = managerName; }
    public String getManagerPassword() { return managerPassword; }
    public void setManagerPassword(String managerPassword) { this.managerPassword = managerPassword; }
    public boolean isOccupied() { return occupied; }
    public void setOccupied(boolean occupied) { this.occupied = occupied; }
    public double getRentalPrice() { return rentalPrice; }
    public void setRentalPrice(double rentalPrice) { this.rentalPrice = rentalPrice; }
    public double getCurrentValue() { return currentValue; }
    public void setCurrentValue(double currentValue) { this.currentValue = currentValue; }
}
