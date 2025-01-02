package org.mihir.udaan_kam1.service.Restaurant;

import org.mihir.udaan_kam1.dto.Restaurant.RestaurantRequest;
import org.mihir.udaan_kam1.dto.Restaurant.RestaurantResponse;

import java.util.List;

public interface RestaurantService {
    List<RestaurantResponse> getAllRestaurants();
    RestaurantResponse getRestaurant(Long id);
    RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest);
    RestaurantResponse updateRestaurant(RestaurantRequest restaurantRequest);
    void deleteRestaurant(Long id);

    List<RestaurantResponse> getRestaurantsByEmployeeUsername(String employeeUsername);
}
