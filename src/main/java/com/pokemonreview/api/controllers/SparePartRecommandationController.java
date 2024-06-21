package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dto.SparePartRecommandationRequestDTO;
import com.pokemonreview.api.dto.responseDTO.VehicleReccmondationResponseDTO;
import com.pokemonreview.api.entity.VehicleSparePartRecommendation;
import com.pokemonreview.api.service.ISparePartRecommandationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/spare-part-recommondation")
@RestController
public class SparePartRecommandationController {

    @Autowired
    ISparePartRecommandationService sparePartRecommandationService;

    @PostMapping("/recommondSparePart/{vehicleId}/{sparePartId}")
    public ResponseEntity<VehicleSparePartRecommendation> reccomandSparePartForVehicle(@RequestBody()SparePartRecommandationRequestDTO requestDTO , @PathVariable("vehicleId") Long vehicleId, @PathVariable("sparePartId") Long sparePartId){
       VehicleSparePartRecommendation  sparePartRecommandations = sparePartRecommandationService.assignReccomondationForVehicle(requestDTO,vehicleId,sparePartId);
       return ResponseEntity.ok(sparePartRecommandations);

    }
    //Get spare part Reccomondation by vehicle Id
    @GetMapping("/getReccomondations/{vehicleId}")
    public List<VehicleReccmondationResponseDTO> getVehicleSparePartRecommondationsByVehicleId(@PathVariable("vehicleId") Long vehicleId){

        List<VehicleSparePartRecommendation> vehicleSparePartRecommendation= sparePartRecommandationService.getSparepartReccomandationsByVehicleId(vehicleId);


        List<VehicleReccmondationResponseDTO> responseDTOs = vehicleSparePartRecommendation.stream()
                .map(recommendation -> {
                    VehicleReccmondationResponseDTO dto = new VehicleReccmondationResponseDTO();
                    dto.setId(recommendation.getId());
                    dto.setVehicleRegNo(recommendation.getVehicle().getRegistrationNumber());
                    dto.setPartName(recommendation.getSparePart().getPartName());
                    dto.setItemCode(recommendation.getSparePart().getItemCode());
                    dto.setPrice(recommendation.getSparePart().getPrice());
                    dto.setCreatedBy(recommendation.getCreatedBy());
                    dto.setCreatedOn(recommendation.getCreatedOn());
                    dto.setModifiedBy(recommendation.getModifiedBy());
                    dto.setModifiedOn(recommendation.getModifiedOn());
                    dto.setReason(recommendation.getRecommendationReason());
                    dto.setRecommondadMonths(recommendation.getRecommendationMonth());
                    return dto;
                })
                .collect(Collectors.toList());

        return responseDTOs;
    }
}
