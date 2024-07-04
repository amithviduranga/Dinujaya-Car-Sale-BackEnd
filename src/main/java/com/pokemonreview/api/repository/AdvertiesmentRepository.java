package com.pokemonreview.api.repository;

import com.pokemonreview.api.entity.Advertiesment;
import com.pokemonreview.api.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertiesmentRepository extends JpaRepository<Advertiesment, Long> {
}
