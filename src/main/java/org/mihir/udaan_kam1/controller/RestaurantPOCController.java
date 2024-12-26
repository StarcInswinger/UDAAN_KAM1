package org.mihir.udaan_kam1.controller;

import org.mihir.udaan_kam1.dto.RestaurantPOC.RestaurantPOCResponse;
import org.mihir.udaan_kam1.dto.RestaurantPOC.RestaurantPOCRequest;
import org.mihir.udaan_kam1.service.RestaurantPOC.RestaurantPOCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant-pocs")
public class RestaurantPOCController {
    private final RestaurantPOCService restaurantPOCService;

    @Autowired
    public RestaurantPOCController(RestaurantPOCService restaurantPOCService) {
        this.restaurantPOCService = restaurantPOCService;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantPOCResponse>> getAllRestaurantPOC() {
        return ResponseEntity.ok(restaurantPOCService.getAllRestaurantPOCs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantPOCResponse> getRestaurantPOC(@PathVariable("id") Long id) {
        return ResponseEntity.ok(restaurantPOCService.getRestaurantPOC(id));
    }

    @PostMapping
    public ResponseEntity<RestaurantPOCResponse> createRestaurantPOC(@RequestBody RestaurantPOCRequest restaurantPOCRequest) {
        return ResponseEntity.ok(restaurantPOCService.createRestaurantPOC(restaurantPOCRequest));
    }

    @PutMapping
    public ResponseEntity<RestaurantPOCResponse> updateRestaurantPOC(@RequestBody RestaurantPOCRequest restaurantPOCRequest) {
        return ResponseEntity.ok(restaurantPOCService.updateRestaurantPOC(restaurantPOCRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurantPOC(@PathVariable("id") Long id) {
        restaurantPOCService.deleteRestaurantPOC(id);
        return ResponseEntity.ok("Restaurant POC deleted with ID: " + id);
    }
}
