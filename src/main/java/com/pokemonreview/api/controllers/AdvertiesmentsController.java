package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dto.VehicleDataRequestDTO;
import com.pokemonreview.api.entity.Advertiesment;
import com.pokemonreview.api.entity.Vehicle;
import com.pokemonreview.api.service.IAdvertiesmentService;
import com.pokemonreview.api.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/advertiesments")
@RestController
public class AdvertiesmentsController {


    @Autowired
    private IAdvertiesmentService advertiesmentService;

    @PostMapping("/createAdvertiesment")
    public Advertiesment saveVehicleMainDetails(@RequestPart("advertiesment") Advertiesment vehicleDto,
                                          @RequestPart("mainImage") MultipartFile mainImage,
                                          @RequestPart("images") List<MultipartFile> images) {


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
        vehicle.setCreatedOn(new Date());
        vehicle.setModifiedOn(new Date());
        vehicle.setCreatedBy("amith1234"); // need  implement later
        vehicle.setModifiedBy(null);


        Advertiesment savedAdvertiesment = advertiesmentService.createAdvertiesment(vehicle, images, mainImage);

        return savedAdvertiesment;
    }

}
