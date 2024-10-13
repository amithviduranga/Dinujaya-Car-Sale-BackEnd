package com.pokemonreview.api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class InvoiceRequestDTO
{
    private Long vehicleId;
    private double discount;
    private String customerFullName;
    private String address;
    private String mobileNumber;
    private String email;
    private String paymentMethod;
    private String PaymentType;
    private String invoicedBy;

}
