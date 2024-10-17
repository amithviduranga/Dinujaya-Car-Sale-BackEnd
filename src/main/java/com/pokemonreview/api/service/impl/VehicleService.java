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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

   public List<Vehicle> getVehicleByType(String category){
      // Retrieve Vehicle From its type from Database
      List<Vehicle> vehiclesByType = vehicleRepository.findAll();

       List <Vehicle>  filteredVehicles = vehiclesByType.stream()
               .filter(vehicle -> category.equalsIgnoreCase(vehicle.getCategory()))
               .collect(Collectors.toList());

      if(filteredVehicles!=null) {
          Collections.reverse(vehiclesByType);
          return filteredVehicles;
      }else{

          return new ArrayList<>();
      }
   }

    @Override
    public boolean deleteVehicle(Long id) {
        // Check if the vehicle exists
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        if (vehicleOptional.isPresent()) {
            // If exists, delete the vehicle (and related images due to CascadeType.ALL)
            vehicleRepository.delete(vehicleOptional.get());
            return true;
        }
        return false;
    }

}
