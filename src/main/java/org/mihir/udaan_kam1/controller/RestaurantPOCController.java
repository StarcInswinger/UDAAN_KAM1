package org.mihir.udaan_kam1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Restaurant POC", description = "Restaurant Point of Contact management APIs (Admin/Manager only)")
@SecurityRequirement(name = "bearerAuth")
public class RestaurantPOCController {
    private final RestaurantPOCService restaurantPOCService;

    @Autowired
    public RestaurantPOCController(RestaurantPOCService restaurantPOCService) {
        this.restaurantPOCService = restaurantPOCService;
    }

    @Operation(
            summary = "Get all restaurant POCs",
            description = "Retrieves all restaurant points of contact in the system. Requires ADMIN or MANAGER role."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved all restaurant POCs",
            content = @Content(schema = @Schema(implementation = RestaurantPOCResponse.class))
    )
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping
    public ResponseEntity<List<RestaurantPOCResponse>> getAllRestaurantPOC() {
        return ResponseEntity.ok(restaurantPOCService.getAllRestaurantPOCs());
    }

    @Operation(
            summary = "Get restaurant POC by ID",
            description = "Retrieves a specific restaurant point of contact by ID. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Restaurant POC found",
                    content = @Content(schema = @Schema(implementation = RestaurantPOCResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurant POC not found",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/{pocId}")
    public ResponseEntity<RestaurantPOCResponse> getRestaurantPOC(
            @Parameter(description = "ID of the restaurant POC", required = true)
            @PathVariable("pocId") Long pocId) {
        return ResponseEntity.ok(restaurantPOCService.getRestaurantPOC(pocId));
    }

    @Operation(
            summary = "Create restaurant POC",
            description = "Creates a new restaurant point of contact in the system. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Restaurant POC created successfully",
                    content = @Content(schema = @Schema(implementation = RestaurantPOCResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PostMapping
    public ResponseEntity<RestaurantPOCResponse> createRestaurantPOC(
            @Parameter(description = "Restaurant POC details", required = true)
            @RequestBody RestaurantPOCRequest restaurantPOCRequest) {
        return ResponseEntity.ok(restaurantPOCService.createRestaurantPOC(restaurantPOCRequest));
    }

    @Operation(
            summary = "Update restaurant POC",
            description = "Updates an existing restaurant point of contact's information. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Restaurant POC updated successfully",
                    content = @Content(schema = @Schema(implementation = RestaurantPOCResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurant POC not found",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantPOCResponse> updateRestaurantPOC(
            @Parameter(description = "ID of the restaurant POC to update", required = true)
            @PathVariable(name = "id") Long pocId,
            @Parameter(description = "Updated restaurant POC details", required = true)
            @RequestBody RestaurantPOCRequest restaurantPOCRequest) {
        return ResponseEntity.ok(restaurantPOCService.updateRestaurantPOC(pocId, restaurantPOCRequest));
    }

    @Operation(
            summary = "Delete restaurant POC",
            description = "Deletes a restaurant point of contact from the system. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restaurant POC deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant POC not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied - Requires ADMIN or MANAGER role", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurantPOC(
            @Parameter(description = "ID of the restaurant POC to delete", required = true)
            @PathVariable("id") Long id) {
        restaurantPOCService.deleteRestaurantPOC(id);
        return ResponseEntity.ok("Restaurant POC deleted with ID: " + id);
    }

    @Operation(
            summary = "Get POCs by restaurant ID",
            description = "Retrieves all points of contact associated with a specific restaurant. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved restaurant POCs",
                    content = @Content(schema = @Schema(implementation = RestaurantPOCResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurant not found",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/by-restaurant/{id}")
    public ResponseEntity<List<RestaurantPOCResponse>> getAllRestaurantPOCsByRestaurantId(
            @Parameter(description = "ID of the restaurant", required = true)
            @PathVariable(name = "id") Long restaurantId) {
        return ResponseEntity.ok(restaurantPOCService.getAllRestaurantPOCsByRestaurantId(restaurantId));
    }
}
