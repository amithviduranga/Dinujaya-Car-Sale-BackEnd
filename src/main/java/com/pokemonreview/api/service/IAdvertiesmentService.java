package com.pokemonreview.api.service;

import com.pokemonreview.api.entity.Advertiesment;
import com.pokemonreview.api.entity.Vehicle;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAdvertiesmentService {

    Advertiesment createAdvertiesment(Advertiesment advertiesment, List<MultipartFile> images, MultipartFile mainImage);
}
