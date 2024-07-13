package com.pokemonreview.api.repository;

import com.pokemonreview.api.entity.AdvertiesmentStatus;
import com.pokemonreview.api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertiesmentStatusRepository extends JpaRepository<AdvertiesmentStatus, Integer> {
}
