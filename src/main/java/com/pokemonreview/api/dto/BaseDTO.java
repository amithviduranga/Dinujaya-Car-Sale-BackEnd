package com.pokemonreview.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class BaseDTO {
    private String createdOn;
    private String modifiedOn;
    private String createdBy;
    private String modifiedBy;
}
