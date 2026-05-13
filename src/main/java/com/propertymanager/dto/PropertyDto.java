package com.propertymanager.dto;

public class PropertyDto {

    private String name;
    private String area;
    private double rentalPrice;
    private boolean occupied;
    private String managerName;
    private String managerPassword;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
    public double getRentalPrice() { return rentalPrice; }
    public void setRentalPrice(double rentalPrice) { this.rentalPrice = rentalPrice; }
    public boolean isOccupied() { return occupied; }
    public void setOccupied(boolean occupied) { this.occupied = occupied; }
    public String getManagerName() { return managerName; }
    public void setManagerName(String managerName) { this.managerName = managerName; }
    public String getManagerPassword() { return managerPassword; }
    public void setManagerPassword(String managerPassword) { this.managerPassword = managerPassword; }
}