package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.SpareParsRequestDTO;
import com.pokemonreview.api.dto.SparePartUpdateRequest;
import com.pokemonreview.api.entity.SparePart;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ISparePartService {

     SparePart saveSparePart(SpareParsRequestDTO requestDTO);

     List<SparePart> getAllSpareParts();

     SparePart updateSparePart(SparePartUpdateRequest updateRequest,Long id);

     ResponseEntity<?> deleteSaprePart(Long id);
}
