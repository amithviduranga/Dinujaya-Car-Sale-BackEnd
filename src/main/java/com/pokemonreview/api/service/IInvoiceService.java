package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.InvoiceRequestDTO;
import com.pokemonreview.api.dto.InvoiceResponseDTO;

public interface IInvoiceService {

    InvoiceResponseDTO generateInvoiceForCustomer(InvoiceRequestDTO request);
}
