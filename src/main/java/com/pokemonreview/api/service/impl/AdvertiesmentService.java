package com.pokemonreview.api.service.impl;

import com.pokemonreview.api.dto.UpdateAddStatusReqDTO;
import com.pokemonreview.api.dto.VehicleDataRequestDTO;
import com.pokemonreview.api.entity.*;
import com.pokemonreview.api.repository.*;
import com.pokemonreview.api.service.IAdvertiesmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class AdvertiesmentService implements IAdvertiesmentService {

    @Autowired
    AdvertiesmentRepository advertiesmentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdvertiesmentIamgeRepository advertiesmentIamgeRepository;

    @Autowired
    AdvertiesmentStatusRepository advertiesmentStatusRepository;

    @Autowired
    EmailService emailService;

    public Advertiesment createAdvertiesment(Advertiesment advertiesment, List<MultipartFile> images, MultipartFile mainImage,int userId){

        try {


        //Retrieve User by user Id

        UserEntity user = userRepository.findById(userId);
        advertiesment.setUser(user);
        advertiesment.setCreatedBy(user.getUsername());
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
        }catch (Exception ex){
            throw ex;
        }
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
        public boolean updateStatus(Long id, String order, UpdateAddStatusReqDTO request){
            Advertiesment advertiesment = advertiesmentRepository.findById(id).get();



            try {
                    if(order.equals("accept")) {
                        advertiesment.setStatus(1);
                        advertiesment.setModifiedBy(request.getModifiedBy());
                        advertiesment.setModifiedOn(new Date());
                        String Subject = "Dinujaya Car Sale Advertiesment | "+advertiesment.getModelName() +" "+ advertiesment.getBrandName()+" - SUCCESS";
                        String successMessage = "Your Advertiesment has been Approved By the Dinujaya Car Sale.";
                        emailService.sendEmail(advertiesment.getUser().getEmail(),Subject,successMessage );
                    }else if(order.equals("reject")) {
                        advertiesment.setStatus(2);
                        advertiesment.setModifiedBy(request.getModifiedBy());
                        advertiesment.setModifiedOn(new Date());
                    }

                    advertiesmentRepository.save(advertiesment);

                AdvertiesmentStatus status =new AdvertiesmentStatus();
                status.setRejectReason(request.getRejectReason());
                status.setEmailSentFlag(true);
                status.setAdvertiesment(advertiesment);
                AdvertiesmentStatus advertiesmentStats= advertiesmentStatusRepository.save(status);
                    return true;
            }catch (Exception ex){

                return false;
            }

    }
}
