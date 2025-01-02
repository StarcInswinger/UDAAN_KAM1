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

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping
    public ResponseEntity<List<RestaurantPOCResponse>> getAllRestaurantPOC() {
        return ResponseEntity.ok(restaurantPOCService.getAllRestaurantPOCs());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/{pocId}")
    public ResponseEntity<RestaurantPOCResponse> getRestaurantPOC(@PathVariable("pocId") Long pocId) {
        return ResponseEntity.ok(restaurantPOCService.getRestaurantPOC(pocId));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PostMapping
    public ResponseEntity<RestaurantPOCResponse> createRestaurantPOC(@RequestBody RestaurantPOCRequest restaurantPOCRequest) {
        return ResponseEntity.ok(restaurantPOCService.createRestaurantPOC(restaurantPOCRequest));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping("/{pocId}")
    public ResponseEntity<RestaurantPOCResponse> updateRestaurantPOC(@PathVariable(name = "pocId") Long pocId,@RequestBody RestaurantPOCRequest restaurantPOCRequest) {
        return ResponseEntity.ok(restaurantPOCService.updateRestaurantPOC(pocId, restaurantPOCRequest));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurantPOC(@PathVariable("id") Long id) {
        restaurantPOCService.deleteRestaurantPOC(id);
        return ResponseEntity.ok("Restaurant POC deleted with ID: " + id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/by-restaurant/{id}")
    public ResponseEntity<List<RestaurantPOCResponse>> getAllRestaurantPOCsByRestaurantId(@PathVariable(name = "id") Long restaurantId) {
        return ResponseEntity.ok(restaurantPOCService.getAllRestaurantPOCsByRestaurantId(restaurantId));
    }
}
