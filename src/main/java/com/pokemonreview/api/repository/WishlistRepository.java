package com.pokemonreview.api.repository;

import com.pokemonreview.api.entity.UserEntity;
import com.pokemonreview.api.entity.Vehicle;
import com.pokemonreview.api.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<WishList, Long> {
    boolean existsByUserAndVehicle(UserEntity user, Vehicle vehicle);
    void deleteByUserAndVehicle(UserEntity user, Vehicle vehicle);
}
