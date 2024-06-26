package com.pokemonreview.api.service;


import com.pokemonreview.api.entity.Vehicle;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IVehicleService {

     Vehicle saveVehicle(Vehicle vehicle, List<MultipartFile> images,MultipartFile mainImage);

     List<Vehicle> getAllListedVehicles();

     Vehicle getVehicleById(long id);

     List<Vehicle> getVehicleByType(String type);

}
