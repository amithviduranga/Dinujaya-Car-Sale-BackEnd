package com.pokemonreview.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpareParsRequestDTO {

    private String itemCode;
    private String vehicleModel;
    private String vehicleBrand;
    private int qty;
    private double price;
    private String partName;
    private String description;
}
