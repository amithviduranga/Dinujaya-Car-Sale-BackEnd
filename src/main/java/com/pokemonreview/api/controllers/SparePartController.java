package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dto.SpareParsRequestDTO;
import com.pokemonreview.api.dto.SparePartUpdateRequest;
import com.pokemonreview.api.dto.VehicleDataRequestDTO;
import com.pokemonreview.api.entity.SparePart;
import com.pokemonreview.api.entity.Vehicle;
import com.pokemonreview.api.service.ISparePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/spare-part")
@RestController
public class SparePartController {
    @Autowired
    ISparePartService sparePartService;

    @PostMapping("/saveSparePart")
    public SparePart saveSparePart(@RequestBody() SpareParsRequestDTO request){


        SparePart savedSparepart = sparePartService.saveSparePart(request);

        return savedSparepart;
    }

    //Get all avaialbe Spare part

    @GetMapping("/getAllSpareParts")
    public ResponseEntity<List<SparePart>> getAllSpareParts(){

        return ResponseEntity.ok(sparePartService.getAllSpareParts());

    }

    @PutMapping("/updateSparePart/{id}")
    public ResponseEntity<SparePart> updateSparePart(@RequestBody() SparePartUpdateRequest updateRequest,@PathVariable("id") Long id){

        return ResponseEntity.ok(sparePartService.updateSparePart(updateRequest,id));

    }

    @DeleteMapping("/DeleteSaprePart/{id}")
    public ResponseEntity<?> deleteSaprepart(@PathVariable Long id) {
        return sparePartService.deleteSaprePart(id);
    }


}
