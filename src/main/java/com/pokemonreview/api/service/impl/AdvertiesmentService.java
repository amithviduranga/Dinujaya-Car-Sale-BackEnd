package com.pokemonreview.api.service.impl;

import com.pokemonreview.api.dto.VehicleDataRequestDTO;
import com.pokemonreview.api.entity.Advertiesment;
import com.pokemonreview.api.entity.AdvertiesmentImage;
import com.pokemonreview.api.entity.Vehicle;
import com.pokemonreview.api.entity.VehicleImage;
import com.pokemonreview.api.repository.AdvertiesmentIamgeRepository;
import com.pokemonreview.api.repository.AdvertiesmentRepository;
import com.pokemonreview.api.repository.VehicleRepository;
import com.pokemonreview.api.service.IAdvertiesmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class AdvertiesmentService implements IAdvertiesmentService {

    @Autowired
    AdvertiesmentRepository advertiesmentRepository;

    @Autowired
    AdvertiesmentIamgeRepository advertiesmentIamgeRepository;

    public Advertiesment createAdvertiesment(Advertiesment advertiesment, List<MultipartFile> images, MultipartFile mainImage){

        //Save vehicle main data
        Advertiesment savedAdvertiesment  =  advertiesmentRepository.save(advertiesment);

        //Save main image
        if(mainImage != null){

            AdvertiesmentImage mainVehicleImage  = new AdvertiesmentImage();
            mainVehicleImage.setFileName(mainImage.getOriginalFilename());
            mainVehicleImage.setFileType(mainImage.getContentType());
            try {
                mainVehicleImage.setData(mainImage.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mainVehicleImage.setMainImage(true);
            mainVehicleImage.setAdvertiesment(savedAdvertiesment);


            advertiesmentIamgeRepository.save(mainVehicleImage);
        }
        if (images != null && !images.isEmpty()) {
            for (MultipartFile file : images) {
                AdvertiesmentImage vehicleImage = new AdvertiesmentImage();
                vehicleImage.setFileName(file.getOriginalFilename());
                vehicleImage.setFileType(file.getContentType());
                try {
                    vehicleImage.setData(file.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                vehicleImage.setMainImage(false);
                vehicleImage.setAdvertiesment(savedAdvertiesment);
                advertiesmentIamgeRepository.save(vehicleImage);
            }
        }

        return savedAdvertiesment;

    }

    public List<Advertiesment> getAllAdvertiesments(){
        // Retrieve all vehivles from database
        List<Advertiesment> allAdvertiesments = advertiesmentRepository.findAll();
        Collections.reverse(allAdvertiesments);
        return allAdvertiesments;
    }
    @Override
    public boolean changePaymentStatus(Long id) {
        Optional<Advertiesment> advertiesmentOptional = advertiesmentRepository.findById(id);
        try {
            if (advertiesmentOptional.isPresent()) {
                Advertiesment advertiesment = advertiesmentOptional.get();
                advertiesment.setPayment("success");
                advertiesmentRepository.save(advertiesment);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {

            return false;
        }
    }
        @Override
        public boolean updateStatus(Long id,String order){
            Optional<Advertiesment> advertiesmentOptional = advertiesmentRepository.findById(id);
            try {
                if (advertiesmentOptional.isPresent()) {
                    Advertiesment advertiesment = advertiesmentOptional.get();
                    if(order.equals("accept")) {
                        advertiesment.setStatus(1);
                    }else if(order.equals("reject")) {
                        advertiesment.setStatus(2);
                    }
                    advertiesmentRepository.save(advertiesment);
                    return true;
                } else {
                    return false;
                }
            }catch (Exception ex){

                return false;
            }

    }
}
