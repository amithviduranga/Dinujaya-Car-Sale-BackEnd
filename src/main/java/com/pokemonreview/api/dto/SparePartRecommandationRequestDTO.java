package com.pokemonreview.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SparePartRecommandationRequestDTO {

    private int reccomondationMonths;
    private String recommendationReason;

}
