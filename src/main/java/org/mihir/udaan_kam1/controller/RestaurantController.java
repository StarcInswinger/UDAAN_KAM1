package org.mihir.udaan_kam1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Restaurant", description = "Restaurant management APIs (Admin/Manager only)")
@SecurityRequirement(name = "bearerAuth")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Operation(
            summary = "Get all restaurants",
            description = "Retrieves all restaurants in the system. Requires ADMIN or MANAGER role."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved all restaurants",
            content = @Content(schema = @Schema(implementation = RestaurantResponse.class))
    )
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurant() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @Operation(
            summary = "Get restaurant by ID",
            description = "Retrieves a specific restaurant by ID. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Restaurant found",
                    content = @Content(schema = @Schema(implementation = RestaurantResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurant not found",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> getRestaurant(
            @Parameter(description = "ID of the restaurant", required = true)
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(restaurantService.getRestaurant(id));
    }

    @Operation(
            summary = "Create restaurant",
            description = "Creates a new restaurant in the system. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Restaurant created successfully",
                    content = @Content(schema = @Schema(implementation = RestaurantResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PostMapping
    public ResponseEntity<RestaurantResponse> createRestaurant(
            @Parameter(description = "Restaurant details", required = true)
            @RequestBody RestaurantRequest restaurantRequest) {
        return ResponseEntity.status(201).body(restaurantService.createRestaurant(restaurantRequest));
    }

    @Operation(
            summary = "Update restaurant",
            description = "Updates an existing restaurant's information. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Restaurant updated successfully",
                    content = @Content(schema = @Schema(implementation = RestaurantResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurant not found",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponse> updateRestaurant(
            @Parameter(description = "ID of the restaurant to update", required = true)
            @PathVariable(name = "id") Long id,
            @Parameter(description = "Updated restaurant details", required = true)
            @RequestBody RestaurantRequest restaurantRequest) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(id, restaurantRequest));
    }

    @Operation(
            summary = "Delete restaurant",
            description = "Deletes a restaurant from the system. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restaurant deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied - Requires ADMIN or MANAGER role", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant(
            @Parameter(description = "ID of the restaurant to delete", required = true)
            @PathVariable("id") Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok("Restaurant deleted with ID: " + id);
    }

    @Operation(
            summary = "Get restaurants by employee",
            description = "Retrieves all restaurants associated with a specific employee. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved restaurants for employee",
                    content = @Content(schema = @Schema(implementation = RestaurantResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Employee not found",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/by-employee/{username}")
    public ResponseEntity<List<RestaurantResponse>> getRestaurantsByEmployeeUsername(
            @Parameter(description = "Username of the employee", required = true)
            @PathVariable(name = "username") String username) {
        return ResponseEntity.ok(restaurantService.getRestaurantsByEmployeeUsername(username));
    }
}
