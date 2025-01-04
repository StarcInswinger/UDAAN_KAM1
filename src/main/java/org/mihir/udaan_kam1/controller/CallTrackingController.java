package org.mihir.udaan_kam1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mihir.udaan_kam1.dto.CallTracking.CallTrackingRequest;
import org.mihir.udaan_kam1.dto.CallTracking.CallTrackingResponse;
import org.mihir.udaan_kam1.service.CallTracking.CallTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/call-tracking")
@Tag(name = "Call Tracking", description = "APIs for managing call tracking records (Admin/Manager only)")
@SecurityRequirement(name = "bearerAuth")
public class CallTrackingController {

    private final CallTrackingService callTrackingService;

    @Autowired
    public CallTrackingController(CallTrackingService callTrackingService) {
        this.callTrackingService = callTrackingService;
    }

    @Operation(
            summary = "Get all call tracking records",
            description = "Retrieves all call tracking records. Requires ADMIN or MANAGER role."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved all call tracking records",
            content = @Content(schema = @Schema(implementation = CallTrackingResponse.class))
    )
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping
    public ResponseEntity<List<CallTrackingResponse>> getAllCallTrackings() {
        return ResponseEntity.ok(callTrackingService.getAllCallTracking());
    }

    @Operation(
            summary = "Get call tracking by ID",
            description = "Retrieves a specific call tracking record by its ID. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Call tracking record found",
                    content = @Content(schema = @Schema(implementation = CallTrackingResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Call tracking record not found",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<CallTrackingResponse> getCallTrackingById(
            @Parameter(description = "ID of the call tracking record", required = true)
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(callTrackingService.getCallTrackingById(id));
    }

    @Operation(
            summary = "Create new call tracking record",
            description = "Creates a new call tracking record. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Call tracking record created successfully",
                    content = @Content(schema = @Schema(implementation = CallTrackingResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PostMapping
    public ResponseEntity<CallTrackingResponse> createCallTracking(
            @RequestBody CallTrackingRequest callTrackingRequest) {
        return ResponseEntity.ok(callTrackingService.createCallTracking(callTrackingRequest));
    }

    @Operation(
            summary = "Update call tracking record",
            description = "Updates an existing call tracking record. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Call tracking record updated successfully",
                    content = @Content(schema = @Schema(implementation = CallTrackingResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Call tracking record not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<CallTrackingResponse> updateCallTracking(
            @Parameter(description = "ID of the call tracking record to update", required = true)
            @PathVariable(name = "id") Long callId,
            @RequestBody CallTrackingRequest callTrackingRequest) {
        return ResponseEntity.ok(callTrackingService.updateCallTracking(callId, callTrackingRequest));
    }

    @Operation(
            summary = "Delete call tracking record",
            description = "Deletes a call tracking record. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Call tracking record deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Call tracking record not found", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCallTracking(
            @Parameter(description = "ID of the call tracking record to delete", required = true)
            @PathVariable("id") Long id) {
        callTrackingService.deleteCallTracking(id);
        return ResponseEntity.ok("Call record deleted with ID: " + id);
    }

    @Operation(
            summary = "Get last call tracking by POC ID",
            description = "Retrieves the most recent call tracking record for a specific POC. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Last call tracking record found",
                    content = @Content(schema = @Schema(implementation = CallTrackingResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No call tracking records found for POC",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/last/by-poc/{pocId}")
    public ResponseEntity<CallTrackingResponse> getLastCallTrackingByPocId(
            @Parameter(description = "ID of the POC", required = true)
            @PathVariable(name = "pocId") Long pocId) {
        return ResponseEntity.ok(callTrackingService.getLastCallTrackingByPOCId(pocId));
    }

    @Operation(
            summary = "Get all call tracking records by POC ID",
            description = "Retrieves all call tracking records for a specific POC. Requires ADMIN or MANAGER role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved all call tracking records for POC",
                    content = @Content(schema = @Schema(implementation = CallTrackingResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "POC not found",
                    content = @Content
            )
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/by-poc/{pocId}")
    public ResponseEntity<List<CallTrackingResponse>> getAllCallTrackingByPocId(
            @Parameter(description = "ID of the POC", required = true)
            @PathVariable(name = "pocId") Long pocId) {
        return ResponseEntity.ok(callTrackingService.getAllCallTrackingsByPOCId(pocId));
    }
}
