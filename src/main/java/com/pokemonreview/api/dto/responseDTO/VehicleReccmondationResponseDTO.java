package com.pokemonreview.api.dto.responseDTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class VehicleReccmondationResponseDTO {
    private Long id;
    private String vehicleRegNo;
    private String itemCode;
    private String partName;
    private double price;
    private Date createdOn;
    private Date modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private int recommondadMonths;
    private String reason;

}
