package com.pokemonreview.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAddStatusReqDTO {
    private String rejectReason;
    private String modifiedBy;
}
