package com.pokemonreview.api.dto.responseDTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
public class SparePartResponseDTO {
    private Long id;
    private String itemCode;
    private String vehicleModel;
    private String vehicleBrand;
    private int qty;
    private double price;
    private String partName;
    private Date createdOn;
    private String description;
    private String fileType;
    private String data;
}
