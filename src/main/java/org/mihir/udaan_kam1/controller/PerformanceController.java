package org.mihir.udaan_kam1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mihir.udaan_kam1.dto.Performance.PerformanceRequest;
import org.mihir.udaan_kam1.dto.Performance.PerformanceResponse;
import org.mihir.udaan_kam1.service.Performance.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/performances")
@Tag(name = "Performance", description = "Performance metrics management APIs (Admin/Manager only)")
@SecurityRequirement(name = "bearerAuth")
public class PerformanceController {

    private final PerformanceService performanceService;

    @Autowired
    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @Operation(
            summary = "Get all performances",
            description = "Retrieves all performance metrics. Requires ADMIN or MANAGER role."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved all performance metrics",
            content = @Content(schema = @Schema(implementation = PerformanceResponse.class))
    )
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping
    public ResponseEntity<List<PerformanceResponse>> getAllPerformances() {
        return ResponseEntity.ok(performanceService.getAllPerformances());
    }

    @Operation(
            summary = "Get performance by ID",
            description = "Retrieves a specific performance record by ID. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Performance record found",
                    content = @Content(schema = @Schema(implementation = PerformanceResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Performance record not found",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<PerformanceResponse> getPerformanceById(
            @Parameter(description = "ID of the performance record", required = true)
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(performanceService.getPerformanceById(id));
    }

    @Operation(
            summary = "Create performance record",
            description = "Creates a new performance record. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Performance record created successfully",
                    content = @Content(schema = @Schema(implementation = PerformanceResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PostMapping
    public ResponseEntity<PerformanceResponse> createPerformance(@RequestBody PerformanceRequest performanceRequest) {
        return ResponseEntity.ok(performanceService.createPerformance(performanceRequest));
    }

    @Operation(
            summary = "Update performance record",
            description = "Updates an existing performance record. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Performance record updated successfully",
                    content = @Content(schema = @Schema(implementation = PerformanceResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Performance record not found",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<PerformanceResponse> updatePerformance(
            @Parameter(description = "ID of the performance record to update", required = true)
            @PathVariable(name = "id") Long performanceId,
            @RequestBody PerformanceRequest performanceRequest) {
        return ResponseEntity.ok(performanceService.updatePerformance(performanceId, performanceRequest));
    }

    @Operation(
            summary = "Delete performance record",
            description = "Deletes a performance record. Requires ADMIN role only."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Performance record deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Performance record not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied - Requires ADMIN role", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerformance(
            @Parameter(description = "ID of the performance record to delete", required = true)
            @PathVariable("id") Long id) {
        performanceService.deletePerformance(id);
        return ResponseEntity.ok("Performance deleted with ID: " + id);
    }

    @Operation(
            summary = "Get performance by restaurant",
            description = "Retrieves performance metrics for a specific restaurant. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Performance record found",
                    content = @Content(schema = @Schema(implementation = PerformanceResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurant not found",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/by-restaurant")
    public ResponseEntity<PerformanceResponse> getPerformanceByRestaurantId(
            @Parameter(description = "ID of the restaurant", required = true)
            @RequestParam(name = "id") Long restaurantId) {
        return ResponseEntity.ok(performanceService.getPerformanceByRestaurantId(restaurantId));
    }

    @Operation(
            summary = "Get high performing accounts by employee",
            description = "Retrieves high performing accounts managed by a specific employee. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved high performing accounts",
                    content = @Content(schema = @Schema(implementation = PerformanceResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Employee not found",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/by-employee/{username}/high")
    public ResponseEntity<List<PerformanceResponse>> fetchHighPerformingAccountsByEmployeeUsername(
            @Parameter(description = "Username of the employee", required = true)
            @PathVariable(name = "username") String username) {
        return ResponseEntity.ok(performanceService.fetchHighPerformingAccountsByEmployeeUsername(username));
    }

    @Operation(
            summary = "Get low performing accounts by employee",
            description = "Retrieves low performing accounts managed by a specific employee. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved low performing accounts",
                    content = @Content(schema = @Schema(implementation = PerformanceResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Employee not found",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/by-employee/{username}/low")
    public ResponseEntity<List<PerformanceResponse>> fetchLowPerformingAccountsByEmployeeUsername(
            @Parameter(description = "Username of the employee", required = true)
            @PathVariable(name = "username") String username) {
        return ResponseEntity.ok(performanceService.fetchLowPerformingAccountsByEmployeeUsername(username));
    }

    @Operation(
            summary = "Reset monthly performance metrics",
            description = "Resets all performance metrics at the start of each month. Automated task running at midnight on the 1st of every month."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Monthly metrics reset successfully"
    )
    @Scheduled(cron = "0 0 0 1 * *")
    @GetMapping("/reset-performances")
    public ResponseEntity<String> resetMonthlyPerformanceMetrics() {
        performanceService.resetMonthlyPerformanceMetrics();
        return ResponseEntity.ok("Monthly Metrics Reset");
    }
}
