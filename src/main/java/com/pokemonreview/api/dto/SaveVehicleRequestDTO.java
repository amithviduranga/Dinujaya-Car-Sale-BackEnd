package com.pokemonreview.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveVehicleRequestDTO {

    private String modelName;
    private String brandName;
    private int manufactureYear;
    private String price;
    private String color;
    private String condition;
    private String modelURL;
    private String fuelType;
    private String description;

}
