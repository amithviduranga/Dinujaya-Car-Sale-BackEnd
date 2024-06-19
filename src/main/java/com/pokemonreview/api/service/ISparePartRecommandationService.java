package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.SparePartRecommandationRequestDTO;
import com.pokemonreview.api.entity.VehicleSparePartRecommendation;

import java.util.List;

public interface ISparePartRecommandationService {

    VehicleSparePartRecommendation assignReccomondationForVehicle(SparePartRecommandationRequestDTO requestDTO, Long vehicleId , Long sparePartId);

    List<VehicleSparePartRecommendation> getSparepartReccomandationsByVehicleId(Long vehicleId);

}
