package com.pokemonreview.api.dto;

import com.pokemonreview.api.entity.Customer;
import com.pokemonreview.api.entity.Vehicle;
import lombok.Data;

@Data
public class InvoiceResponseDTO {
    private Customer customerData;
    private Vehicle vehicleData;
}
