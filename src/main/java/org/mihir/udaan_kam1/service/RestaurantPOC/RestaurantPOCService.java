package org.mihir.udaan_kam1.service.RestaurantPOC;

import org.mihir.udaan_kam1.dto.RestaurantPOC.RestaurantPOCRequest;
import org.mihir.udaan_kam1.dto.RestaurantPOC.RestaurantPOCResponse;

import java.util.List;

public interface RestaurantPOCService {
    List<RestaurantPOCResponse> getAllRestaurantPOCs();
    RestaurantPOCResponse getRestaurantPOC(Long id);
    RestaurantPOCResponse createRestaurantPOC(RestaurantPOCRequest restaurantPOCRequest);
    RestaurantPOCResponse updateRestaurantPOC(RestaurantPOCRequest restaurantPOCRequest);
    void deleteRestaurantPOC(Long id);
}
