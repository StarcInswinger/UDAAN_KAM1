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

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurant() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> getRestaurant(@PathVariable("id") Long id) {
        return ResponseEntity.ok(restaurantService.getRestaurant(id));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PostMapping
    public ResponseEntity<RestaurantResponse> createRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        return ResponseEntity.status(201).body(restaurantService.createRestaurant(restaurantRequest));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping
    public ResponseEntity<RestaurantResponse> updateRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantRequest));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable("id") Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok("Restaurant deleted with ID: " + id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/by-employee/{username}")
    public ResponseEntity<List<RestaurantResponse>> getRestaurantsByEmployeeUsername(@PathVariable(name = "username") String username) {
        return ResponseEntity.ok(restaurantService.getRestaurantsByEmployeeUsername(username));
    }
}
