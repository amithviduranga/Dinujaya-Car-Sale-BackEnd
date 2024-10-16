package com.pokemonreview.api.controllers;

import com.pokemonreview.api.entity.UserEntity;
import com.pokemonreview.api.entity.Vehicle;
import com.pokemonreview.api.repository.UserRepository;
import com.pokemonreview.api.service.IWishListService;
import com.pokemonreview.api.service.impl.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private IWishListService wishlistService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/{vehicleId}/{userId}")
    public ResponseEntity<?> checkIfWishlisted(@PathVariable Long vehicleId, @PathVariable int userId) {
        UserEntity user = userRepository.findById(userId);
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);

        boolean isWishlisted = wishlistService.isVehicleWishlisted(user, vehicle);
        return ResponseEntity.ok().body("{\"isWishlisted\": " + isWishlisted + "}");
    }

    @PostMapping("/{vehicleId}/{userId}")
    public ResponseEntity<?> addToWishlist(@PathVariable Long vehicleId, @PathVariable int userId) {
        UserEntity user = userRepository.findById(userId);
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);

        wishlistService.addToWishlist(user, vehicle);
        return ResponseEntity.ok().body("{\"message\": \"Vehicle added to wishlist\"}");
    }

    @DeleteMapping("/{vehicleId}/{userId}")
    public ResponseEntity<?> removeFromWishlist(@PathVariable Long vehicleId, @PathVariable int userId) {
        UserEntity user = userRepository.findById(userId);
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);

        wishlistService.removeFromWishlist(user, vehicle);
        return ResponseEntity.ok().body("{\"message\": \"Vehicle removed from wishlist\"}");
    }
}