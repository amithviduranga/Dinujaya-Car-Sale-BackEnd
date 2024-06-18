package com.pokemonreview.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleDataRequestDTO {
    private String modelName;
    private String brandName;
    private String provinceCode;
    private String registrationNumber;
    private int manufactureYear;
    private double price;
    private int mileage;
    private String color;
    private String fuelType;
    private String condition;
    private String description;
}