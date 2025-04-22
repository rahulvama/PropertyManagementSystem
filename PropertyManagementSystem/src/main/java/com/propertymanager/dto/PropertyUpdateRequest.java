package com.propertymanager.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class PropertyUpdateRequest {
    @NotBlank(message = "Manager name is required")
    private String managerName;

    @NotBlank(message = "Manager password is required")
    private String managerPassword;

    @Min(value = 0, message = "Updated value must be positive")
    private double updatedValue;

    public String getManagerName() { return managerName; }
    public void setManagerName(String managerName) { this.managerName = managerName; }
    public String getManagerPassword() { return managerPassword; }
    public void setManagerPassword(String managerPassword) { this.managerPassword = managerPassword; }
    public double getUpdatedValue() { return updatedValue; }
    public void setUpdatedValue(double updatedValue) { this.updatedValue = updatedValue; }
}