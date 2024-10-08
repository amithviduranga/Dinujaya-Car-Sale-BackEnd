package com.pokemonreview.api.service.impl;

import com.pokemonreview.api.dto.SpareParsRequestDTO;
import com.pokemonreview.api.dto.SparePartUpdateRequest;
import com.pokemonreview.api.entity.SparePart;
import com.pokemonreview.api.repository.SparePartRepository;
import com.pokemonreview.api.service.ISparePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SparePartService implements ISparePartService {


    @Autowired
    SparePartRepository sparePartRepository;

    public SparePart saveSparePart(SpareParsRequestDTO requestDTO){

        SparePart sparePart = new SparePart();
        sparePart.setPartName(requestDTO.getPartName());
        sparePart.setPrice(requestDTO.getPrice());
        sparePart.setAvailableQty(requestDTO.getQty());
        sparePart.setItemCode(requestDTO.getItemCode());
        sparePart.setDescription(requestDTO.getDescription());
        sparePart.setVehicleModel(requestDTO.getVehicleModel());
        sparePart.setVehicleBrand(requestDTO.getVehicleBrand());
        sparePart.setCreatedOn(new Date());
        sparePart.setCreatedBy(null);
        sparePart.setModifiedOn(new Date());
        sparePart.setModifiedBy(null);

        SparePart savedSparePart = sparePartRepository.save(sparePart);

      return savedSparePart;
    }

    public List<SparePart> getAllSpareParts(){

        List<SparePart> spareParts = sparePartRepository.findAll();

        return spareParts;

    }

    @Override
    public SparePart updateSparePart(SparePartUpdateRequest updateRequest,Long id) {
        //find spare part by Id
        SparePart retrievedSparePart = (SparePart) sparePartRepository.findAllById(id);

        int availableQty = retrievedSparePart.getAvailableQty();
        int newQty = availableQty+ updateRequest.getNewQty();

        retrievedSparePart.setPartName(updateRequest.getPartName());
        retrievedSparePart.setPrice(updateRequest.getPrice());
        retrievedSparePart.setAvailableQty(newQty);
        retrievedSparePart.setDescription(updateRequest.getDescription());
        retrievedSparePart.setModifiedBy(null);
        retrievedSparePart.setModifiedOn(new Date());

        SparePart updatedSparePart = sparePartRepository.save(retrievedSparePart);

        return  updatedSparePart;
    }
    
    @Override
    public ResponseEntity<?> deleteSaprePart(Long id){
        try {
            // Check if the spare part exists
            Optional<SparePart> sparePartOptional = sparePartRepository.findById(id);

            if (sparePartOptional.isPresent()) {
                // If it exists, delete the spare part
                sparePartRepository.deleteById(id);
                // Return a success response
                return ResponseEntity.ok().body("Spare part deleted successfully");
            } else {
                // If the spare part doesn't exist, return a not found response
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Spare part not found");
            }
        } catch (Exception e) {
            // Handle any exceptions and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while deleting spare part");
        }
    }
}
