package com.pokemonreview.api.service.impl;

import com.pokemonreview.api.dto.SparePartRecommandationRequestDTO;
import com.pokemonreview.api.entity.SparePart;
import com.pokemonreview.api.entity.Vehicle;
import com.pokemonreview.api.entity.VehicleSparePartRecommendation;
import com.pokemonreview.api.repository.SparePartReccomandationRepository;
import com.pokemonreview.api.repository.SparePartRepository;
import com.pokemonreview.api.repository.VehicleRepository;
import com.pokemonreview.api.service.ISparePartRecommandationService;
import com.pokemonreview.api.service.ISparePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SparePartRecommandationService implements ISparePartRecommandationService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    SparePartRepository sparePartRepository;

    @Autowired
    SparePartReccomandationRepository sparePartReccomandationRepository;


    public VehicleSparePartRecommendation assignReccomondationForVehicle(SparePartRecommandationRequestDTO requestDTO,Long vehicleId , Long sparePartId){

        VehicleSparePartRecommendation savedVehicleSparePartRecommendation = null;

        Optional<Vehicle> OPvehicle = vehicleRepository.findById(vehicleId);
        Optional<SparePart> OPsparePart = sparePartRepository.findById(sparePartId);

        if (OPvehicle.isPresent() && OPsparePart.isPresent()) {
            Vehicle vehicle = OPvehicle.get();
            SparePart sparePart = OPsparePart.get();

            VehicleSparePartRecommendation vehicleSparePartRecommendation = new VehicleSparePartRecommendation();
            vehicleSparePartRecommendation.setVehicle(vehicle);
            vehicleSparePartRecommendation.setSparePart(sparePart);
            vehicleSparePartRecommendation.setRecommendationMonth(requestDTO.getReccomondationMonths());
            vehicleSparePartRecommendation.setRecommendationReason(requestDTO.getRecommendationReason());
            vehicleSparePartRecommendation.setCreatedOn(new Date());
            vehicleSparePartRecommendation.setModifiedBy(null);
            vehicleSparePartRecommendation.setModifiedOn(new Date());
            vehicleSparePartRecommendation.setCreatedBy(null);


            // Assuming you have a repository to save the recommendation
          savedVehicleSparePartRecommendation = sparePartReccomandationRepository.save(vehicleSparePartRecommendation);
        } else {
            // Handle the case where vehicle or spare part is not found
            if (!OPvehicle.isPresent()) {
                System.out.println("Vehicle not found with ID: " + vehicleId);
            }
            if (!OPsparePart.isPresent()) {
                System.out.println("Spare part not found with ID: " + sparePartId);
            }
        }

       return savedVehicleSparePartRecommendation;
    }

    public List<VehicleSparePartRecommendation> getSparepartReccomandationsByVehicleId(Long vehicleId){

        List<VehicleSparePartRecommendation> sparePartRecommendations = sparePartReccomandationRepository.findByVehicleId(vehicleId);

        return sparePartRecommendations;

    }

}
