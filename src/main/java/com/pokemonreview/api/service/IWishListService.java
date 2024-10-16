package com.pokemonreview.api.service;

import com.pokemonreview.api.entity.UserEntity;
import com.pokemonreview.api.entity.Vehicle;
import com.pokemonreview.api.entity.WishList;

public interface IWishListService {

     boolean isVehicleWishlisted(UserEntity user, Vehicle vehicle);


     void addToWishlist(UserEntity user, Vehicle vehicle);

     void removeFromWishlist(UserEntity user, Vehicle vehicle);

}
