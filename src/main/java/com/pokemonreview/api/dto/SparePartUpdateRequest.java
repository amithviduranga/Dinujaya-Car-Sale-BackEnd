package com.pokemonreview.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SparePartUpdateRequest {

    private String partName;
    private double price;
    private int newQty;
    private String description;
}
