package org.mihir.udaan_kam1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mihir.udaan_kam1.dto.CallReminder.CallReminderRequest;
import org.mihir.udaan_kam1.dto.CallReminder.CallReminderResponse;
import org.mihir.udaan_kam1.service.CallReminder.CallReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/call-reminders")
@Tag(name = "Call Reminders", description = "APIs for managing call reminders")
public class CallReminderController {

    private final CallReminderService callReminderService;

    @Autowired
    public CallReminderController(CallReminderService callReminderService) {
        this.callReminderService = callReminderService;
    }

    @Operation(summary = "Get all call reminders", description = "Retrieves a list of all call reminders in the system")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all call reminders",
            content = @Content(schema = @Schema(implementation = CallReminderResponse.class)))
    @GetMapping
    public ResponseEntity<List<CallReminderResponse>> getCallReminders() {
        return ResponseEntity.ok(callReminderService.getAllCallReminders());
    }

    @Operation(summary = "Get call reminder by ID", description = "Retrieves a specific call reminder using its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Call reminder found",
                    content = @Content(schema = @Schema(implementation = CallReminderResponse.class))),
            @ApiResponse(responseCode = "404", description = "Call reminder not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CallReminderResponse> getCallReminderById(
            @Parameter(description = "ID of the call reminder", required = true)
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(callReminderService.getCallReminderById(id));
    }

    @Operation(summary = "Create new call reminder", description = "Creates a new call reminder in the system")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Call reminder created successfully",
                    content = @Content(schema = @Schema(implementation = CallReminderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CallReminderResponse> createCallReminder(
            @RequestBody CallReminderRequest callReminderRequest) {
        return ResponseEntity.ok(callReminderService.createCallReminder(callReminderRequest));
    }

    @Operation(summary = "Update call reminder", description = "Updates an existing call reminder")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Call reminder updated successfully",
                    content = @Content(schema = @Schema(implementation = CallReminderResponse.class))),
            @ApiResponse(responseCode = "404", description = "Call reminder not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CallReminderResponse> updateCallTracking(
            @Parameter(description = "ID of the call reminder to update", required = true)
            @PathVariable(name = "id") Long reminderId,
            @RequestBody CallReminderRequest callReminderRequest) {
        return ResponseEntity.ok(callReminderService.updateCallReminder(reminderId, callReminderRequest));
    }

    @Operation(summary = "Delete call reminder", description = "Deletes a call reminder from the system")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Call reminder deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Call reminder not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCallReminder(
            @Parameter(description = "ID of the call reminder to delete", required = true)
            @PathVariable("id") Long id) {
        callReminderService.deleteCallReminder(id);
        return ResponseEntity.ok("Call Reminder deleted with ID: " + id);
    }

    @Operation(summary = "Get call reminders by employee",
            description = "Retrieves all call reminders associated with a specific employee")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved employee's call reminders",
                    content = @Content(schema = @Schema(implementation = CallReminderResponse.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)
    })
    @GetMapping("/by-employee/{username}")
    public ResponseEntity<List<CallReminderResponse>> getCallRemindersByEmployee(
            @Parameter(description = "Username of the employee", required = true)
            @PathVariable(name = "username") String username) {
        return ResponseEntity.ok(callReminderService.getCallRemindersByEmployee(username));
    }
}
