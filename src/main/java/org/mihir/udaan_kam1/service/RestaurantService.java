package org.mihir.udaan_kam1.service;

import org.mihir.udaan_kam1.dto.RestaurantRequest;
import org.mihir.udaan_kam1.dto.RestaurantResponse;

import java.util.List;

public interface RestaurantService {
    List<RestaurantResponse> getAllRestaurants();
    RestaurantResponse getRestaurant(Long id);
    RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest);
    RestaurantResponse updateRestaurant(RestaurantRequest restaurantRequest);
    void deleteRestaurant(Long id);
}
