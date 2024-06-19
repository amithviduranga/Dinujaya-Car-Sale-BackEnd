package com.pokemonreview.api.repository;

import com.pokemonreview.api.entity.SparePart;
import com.pokemonreview.api.entity.VehicleSparePartRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SparePartReccomandationRepository extends JpaRepository<VehicleSparePartRecommendation, Long> {

    List<VehicleSparePartRecommendation> findByVehicleId(Long vehicleId);
}
