package com.pokemonreview.api.service.impl;

import com.pokemonreview.api.entity.UserEntity;
import com.pokemonreview.api.entity.Vehicle;
import com.pokemonreview.api.entity.WishList;
import com.pokemonreview.api.repository.WishlistRepository;
import com.pokemonreview.api.service.IWishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class WishlistService implements IWishListService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public boolean isVehicleWishlisted(UserEntity user, Vehicle vehicle) {
        return wishlistRepository.existsByUserAndVehicle(user, vehicle);
    }

    public void addToWishlist(UserEntity user, Vehicle vehicle) {
        if (!isVehicleWishlisted(user, vehicle)) {
            WishList wishlist = new WishList();
            wishlist.setUser(user);
            wishlist.setVehicle(vehicle);
            wishlistRepository.save(wishlist);
        }
    }
    @Transactional
    public void removeFromWishlist(UserEntity user, Vehicle vehicle) {
        wishlistRepository.deleteByUserAndVehicle(user, vehicle);
    }
}