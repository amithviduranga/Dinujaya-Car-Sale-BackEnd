package com.pokemonreview.api.controllers;


import com.pokemonreview.api.dto.SaveVehicleRequestDTO;
import com.pokemonreview.api.dto.VehicleDataRequestDTO;
import com.pokemonreview.api.entity.Vehicle;
import com.pokemonreview.api.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/vehicle")
@RestController
public class VehicleController {

    @Autowired
    private IVehicleService vehicleService;

    //Save vehicle details
    @PostMapping("/saveVehicleDetails")
    public Vehicle saveVehicleMainDetails(@RequestPart("vehicle") VehicleDataRequestDTO vehicleDto,
                                                     @RequestPart("mainImage") MultipartFile mainImage,
                                                     @RequestPart("images") List<MultipartFile> images){


            Vehicle vehicle = new Vehicle();
            vehicle.setModelName(vehicleDto.getModelName());
            vehicle.setBrandName(vehicleDto.getBrandName());
            vehicle.setProvinceCode(vehicleDto.getProvinceCode());
            vehicle.setRegistrationNumber(vehicleDto.getRegistrationNumber());
            vehicle.setManufactureYear(vehicleDto.getManufactureYear());
            vehicle.setPrice(vehicleDto.getPrice());
            vehicle.setMileage(vehicleDto.getMileage());
            vehicle.setColor(vehicleDto.getColor());
            vehicle.setFuelType(vehicleDto.getFuelType());
            vehicle.setVehicleCondition(vehicleDto.getCondition());
            vehicle.setDescription(vehicleDto.getDescription());
            vehicle.setCategory(vehicleDto.getCategory());
            vehicle.setModelURL(vehicleDto.getModelURL());
            vehicle.setLatitude(vehicleDto.getLatitude());
            vehicle.setLongitude(vehicleDto.getLongitude());
            vehicle.setSelectedYard(vehicleDto.getSelectedYard());
            vehicle.setCreatedOn(new Date());
            vehicle.setModifiedOn(new Date());
            vehicle.setCreatedBy("amith1234"); // need  implement later
            vehicle.setModifiedBy(null);


        Vehicle savedVehicle = vehicleService.saveVehicle(vehicle, images, mainImage);

        return savedVehicle;
    }

    //get all listed vehicle data

    @GetMapping()
    public ResponseEntity<List<Vehicle>> getAllListedVehicles(){

       return ResponseEntity.ok(vehicleService.getAllListedVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") Long id){

        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

 // Get Vehilces by Vehicle type
    @GetMapping("/category")
    public ResponseEntity<List<Vehicle>> getVehiclesByType(@RequestParam("type") String type){

        return  ResponseEntity.ok(vehicleService.getVehicleByType(type));
    }



}
