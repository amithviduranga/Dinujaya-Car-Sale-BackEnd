package com.pokemonreview.api.service.impl;

import com.pokemonreview.api.dto.InvoiceRequestDTO;
import com.pokemonreview.api.dto.InvoiceResponseDTO;
import com.pokemonreview.api.entity.Customer;
import com.pokemonreview.api.entity.Vehicle;
import com.pokemonreview.api.entity.VehicleSparePartRecommendation;
import com.pokemonreview.api.repository.CustomerRepository;
import com.pokemonreview.api.repository.SparePartReccomandationRepository;
import com.pokemonreview.api.repository.VehicleRepository;
import com.pokemonreview.api.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private  SparePartReccomandationRepository sparePartReccomandationRepository;

    @Autowired
    private TaskScheduler taskScheduler;
    @Transactional
    public InvoiceResponseDTO generateInvoiceForCustomer(InvoiceRequestDTO request) {
        // Fetch the vehicle by ID
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        // Create new Customer entry
        Customer customer = new Customer();
        customer.setName(request.getCustomerFullName());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setDiscountOffered(request.getDiscount());
        customer.setMobileNo(request.getMobileNumber());
        customer.setPaymentMethod(request.getPaymentMethod());
        customer.setPaymentType(request.getPaymentType());
        customer.setSelledPrice(String.valueOf((vehicle.getPrice()-request.getDiscount())));
        customer.setInvoicedBy(request.getInvoicedBy());
        customer.setPurchaseDate(new Date());
        customer.setVehicle(vehicle);

        // Save customer details
        customerRepository.save(customer);

        // Mark vehicle as sold
        vehicle.setSold(true);
        vehicleRepository.save(vehicle);

        InvoiceResponseDTO responseDTO = new InvoiceResponseDTO();
        responseDTO.setCustomerData(customer);
        responseDTO.setVehicleData(vehicle);
        //send an email to customer
        String Subject = "Congratulations !! " +vehicle.getModelName()+" "+vehicle.getBrandName() + " Purchase Details";
        String successMessage = "Dear "+customer.getName()+",  \n\nPlease refer following purchase details : \n\n\n"+
                "Vehicle Name : "+vehicle.getBrandName()+"  "+vehicle.getModelName()+"\n"+
                "Fule Type : "+vehicle.getFuelType()+"\n"+
                "Payment Method : "+customer.getPaymentMethod()+"\n"+
                "Payment Type : "+customer.getPaymentType()+"\n"+
                "Milage : "+vehicle.getMileage()+"\n"+
                "Registerd Year : "+vehicle.getRegisteredYear()+"\n\n\n\n\n"+

                "Greetins from Dinujaya car sale team....";
        emailService.sendEmail(request.getEmail(),Subject,successMessage );

        //Create Email Sheduler to send spare part Recomondation details to customer

        //Retrieve Reccomondation Details
        List<VehicleSparePartRecommendation> sparePartRecommendations = sparePartReccomandationRepository.findByVehicleId(vehicle.getId());

        for (VehicleSparePartRecommendation recommendation : sparePartRecommendations) {
            scheduleEmail(recommendation.getVehicle().getId(), recommendation, customer.getPurchaseDate(),customer);
        }


        return responseDTO;
    }

    private void scheduleEmail(Long vehicleId, VehicleSparePartRecommendation sparePartRecommendation, Date soldDate,Customer customer) {
        // Calculate when to send the email
        try {
            LocalDate soldLocalDate = soldDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Step 2: Add months to calculate the email send date
          //  LocalDate sendEmailDate = soldLocalDate.plusMonths(sparePartRecommendation.getRecommendationMonth());
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime sendEmailDate = now.plusMinutes(1);
            // Step 3: Convert the send date back to Date (if required)
           // Date sendEmailDateAsDate = Date.from(sendEmailDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            Date sendEmailDateAsDate = Date.from(sendEmailDate.atZone(ZoneId.systemDefault()).toInstant());
            Vehicle vehicle = vehicleRepository.findById(vehicleId)
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));

            String Subject = vehicle.getBrandName()+" "+vehicle.getModelName()+" "+"Spare Part Replacement Notification";
            String body = "Dear "+customer.getName()+"\n\n"+"We have noticed that this is time to Replace "+sparePartRecommendation.getSparePart().getPartName()+ " of you vehicle\n\n\n" +
                    "After comprehensive inspection our techinical team has predicted that the aforementioned spare part should be replaced at this point due to "+sparePartRecommendation.getRecommendationReason() +".\n\n\n" +
                    "If you're considering replacing it, the Dinujaya Car Sale team is ready to assist you with a special discount and high-quality service\n\n" +
                    "Contact us Today\n\n\n\n\n" +
                    "Thank you\n" +
                    "Dinujaya car Sale Team";

            // Schedule the task

            taskScheduler.schedule(() -> emailService.sendEmail(customer.getEmail(), Subject, body), sendEmailDateAsDate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}


