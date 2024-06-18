package com.pokemonreview.api.service.impl;

import com.pokemonreview.api.configuration.ModelMapperConfiguration;
import com.pokemonreview.api.dto.VehicleDataRequestDTO;
import com.pokemonreview.api.entity.Vehicle;
import com.pokemonreview.api.entity.VehicleImage;
import com.pokemonreview.api.repository.VehicleImageRepository;
import com.pokemonreview.api.repository.VehicleRepository;
import com.pokemonreview.api.service.IVehicleService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class VehicleService implements IVehicleService {

  @Autowired
  private VehicleRepository vehicleRepository;
  @Autowired
  private VehicleImageRepository vehicleImageRepository;

  @Autowired
  private ModelMapperConfiguration modelMapperConfiguration;

  public Vehicle saveVehicle(Vehicle vehicle, List<MultipartFile> images, MultipartFile mainImage) {

      //Save vehicle main data
      Vehicle savedVehicle  = vehicleRepository.save(vehicle);

      //Save main image
      if(mainImage != null){

          VehicleImage mainVehicleImage  = new VehicleImage();
          mainVehicleImage.setFileName(mainImage.getOriginalFilename());
          mainVehicleImage.setFileType(mainImage.getContentType());
          try {
              mainVehicleImage.setData(mainImage.getBytes());
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
          mainVehicleImage.setMainImage(true);
          mainVehicleImage.setVehicle(savedVehicle);


          vehicleImageRepository.save(mainVehicleImage);
      }
      if (images != null && !images.isEmpty()) {
          for (MultipartFile file : images) {
              VehicleImage vehicleImage = new VehicleImage();
              vehicleImage.setFileName(file.getOriginalFilename());
              vehicleImage.setFileType(file.getContentType());
              try {
                  vehicleImage.setData(file.getBytes());
              } catch (IOException e) {
                  throw new RuntimeException(e);
              }
              vehicleImage.setMainImage(false);
              vehicleImage.setVehicle(savedVehicle);
              vehicleImageRepository.save(vehicleImage);
          }
      }

      return savedVehicle;
  }

  public List<Vehicle> getAllListedVehicles(){
     // Retrieve all vehivles from database
     List<Vehicle> allVehicles = vehicleRepository.findAll();
     VehicleDataRequestDTO vehicleDataRequestDTO = new VehicleDataRequestDTO();
      Collections.reverse(allVehicles);
      return allVehicles;
  }

    public Vehicle getVehicleById(long id){
        // Retrieve  vehivle by id  from database
        Vehicle vehicle = (Vehicle) vehicleRepository.findAllById(id);


        return vehicle;
    }



}
