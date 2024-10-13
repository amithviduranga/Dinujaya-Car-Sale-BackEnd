package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dto.InvoiceRequestDTO;
import com.pokemonreview.api.dto.InvoiceResponseDTO;
import com.pokemonreview.api.service.IInvoiceService;
import com.pokemonreview.api.service.impl.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private IInvoiceService invoiceService;

    @PostMapping("/generate")
    public ResponseEntity<InvoiceResponseDTO> generateInvoice(@RequestBody InvoiceRequestDTO request) {

       InvoiceResponseDTO invoiceResponseDTO = invoiceService.generateInvoiceForCustomer(request);

        return ResponseEntity.ok(invoiceResponseDTO);
    }
}