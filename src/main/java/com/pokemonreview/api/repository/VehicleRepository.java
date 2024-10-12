package com.pokemonreview.api.repository;

import com.pokemonreview.api.entity.Vehicle;
import com.pokemonreview.api.entity.VehicleImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {


    Object findAllById(long id);

    Object findByCategory(String type);


}
