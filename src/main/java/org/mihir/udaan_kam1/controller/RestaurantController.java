package org.mihir.udaan_kam1.controller;

import org.mihir.udaan_kam1.dto.RestaurantResponse;
import org.mihir.udaan_kam1.dto.RestaurantRequest;
import org.mihir.udaan_kam1.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<RestaurantResponse> getAllRestaurant() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public RestaurantResponse getRestaurant(@PathVariable("id") Long id) {
        return restaurantService.getRestaurant(id);
    }

    @PostMapping
    public RestaurantResponse createRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        return restaurantService.createRestaurant(restaurantRequest);
    }

    @PutMapping
    public RestaurantResponse updateRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        return restaurantService.updateRestaurant(restaurantRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable("id") Long id) {
        restaurantService.deleteRestaurant(id);
    }
}
