package org.mihir.udaan_kam1.controller;

import org.mihir.udaan_kam1.dto.Restaurant.RestaurantResponse;
import org.mihir.udaan_kam1.dto.Restaurant.RestaurantRequest;
import org.mihir.udaan_kam1.service.Restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurant() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> getRestaurant(@PathVariable("id") Long id) {
        return ResponseEntity.ok(restaurantService.getRestaurant(id));
    }

    @PostMapping
    public ResponseEntity<RestaurantResponse> createRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        return ResponseEntity.ok(restaurantService.createRestaurant(restaurantRequest));
    }

    @PutMapping
    public ResponseEntity<RestaurantResponse> updateRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable("id") Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok("Restaurant deleted with ID: " + id);
    }
}
