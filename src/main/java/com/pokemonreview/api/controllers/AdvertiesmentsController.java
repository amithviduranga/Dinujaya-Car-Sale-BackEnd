package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dto.UpdateAddStatusReqDTO;
import com.pokemonreview.api.dto.VehicleDataRequestDTO;
import com.pokemonreview.api.entity.Advertiesment;
import com.pokemonreview.api.entity.Vehicle;
import com.pokemonreview.api.service.IAdvertiesmentService;
import com.pokemonreview.api.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/advertiesments")
@RestController
public class AdvertiesmentsController {


    @Autowired
    private IAdvertiesmentService advertiesmentService;


    @PostMapping("/createAdvertiesment/{userId}")
    public Advertiesment saveVehicleMainDetails(@RequestPart("advertiesment") Advertiesment vehicleDto,
                                          @RequestPart("mainImage") MultipartFile mainImage,
                                          @RequestPart("images") List<MultipartFile> images,
                                                @PathVariable("userId") int userId) {


        Advertiesment vehicle = new Advertiesment();
        vehicle.setModelName(vehicleDto.getModelName());
        vehicle.setBrandName(vehicleDto.getBrandName());
        vehicle.setProvinceCode(vehicleDto.getProvinceCode());
        vehicle.setRegistrationNumber(vehicleDto.getRegistrationNumber());
        vehicle.setManufactureYear(vehicleDto.getManufactureYear());
        vehicle.setPrice(vehicleDto.getPrice());
        vehicle.setMileage(vehicleDto.getMileage());
        vehicle.setContactNo(vehicle.getContactNo());
        vehicle.setLocation(vehicle.getLocation());
        vehicle.setColor(vehicleDto.getColor());
        vehicle.setFuelType(vehicleDto.getFuelType());
        vehicle.setVehicle_Condition(vehicleDto.getVehicle_Condition());
        vehicle.setDescription(vehicleDto.getDescription());
        vehicle.setCategory(vehicleDto.getCategory());
        vehicle.setContactNo(vehicleDto.getContactNo());
        vehicle.setLocation(vehicleDto.getLocation());
        vehicle.setRegisteredYear(vehicleDto.getRegisteredYear());
        vehicle.setVehicle_Condition(vehicleDto.getVehicle_Condition());
        vehicle.setStatus(vehicleDto.getStatus());
        vehicle.setCreatedOn(new Date());
        vehicle.setModifiedOn(new Date());
        vehicle.setModifiedBy(null);


        Advertiesment savedAdvertiesment = advertiesmentService.createAdvertiesment(vehicle, images, mainImage ,userId);

       return savedAdvertiesment;
    }
    @GetMapping("/getAllAdvertiesments")
    public ResponseEntity<List<Advertiesment>> getAllListedVehicles(){

        return ResponseEntity.ok(advertiesmentService.getAllAdvertiesments());
    }

    @PostMapping("changePaymentStatus/{id}")
    public boolean changePaymentStatus(@PathVariable(required = true)Long id){

        boolean value = advertiesmentService.changePaymentStatus(id);
        return value;
    }
    @PostMapping("updateStatus/{id}/{status}")
    public boolean updateStatus(@PathVariable(required = true)Long id,@PathVariable(required = true) String status, @RequestBody(required = false) UpdateAddStatusReqDTO request){
        boolean value = advertiesmentService.updateStatus(id,status,request);
        return value;
    }


}
