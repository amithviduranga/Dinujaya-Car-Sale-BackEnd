package com.pokemonreview.api.repository;

import com.pokemonreview.api.entity.SparePart;
import com.pokemonreview.api.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SparePartRepository extends JpaRepository<SparePart, Long> {


    Object findAllById(long id);

}
