package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.SpareParsRequestDTO;
import com.pokemonreview.api.dto.SparePartUpdateRequest;
import com.pokemonreview.api.entity.SparePart;

import java.util.List;

public interface ISparePartService {

     SparePart saveSparePart(SpareParsRequestDTO requestDTO);

     List<SparePart> getAllSpareParts();

     SparePart updateSparePart(SparePartUpdateRequest updateRequest,Long id);
}
