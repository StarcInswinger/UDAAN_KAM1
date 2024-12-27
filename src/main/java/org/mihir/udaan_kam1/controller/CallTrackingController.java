package org.mihir.udaan_kam1.controller;

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
public class CallTrackingController {

    private final CallTrackingService callTrackingService;

    @Autowired
    public CallTrackingController(CallTrackingService callTrackingService) {
        this.callTrackingService = callTrackingService;
    }

    @GetMapping
    public ResponseEntity<List<CallTrackingResponse>> getAllCallTrackings() {
        return ResponseEntity.ok(callTrackingService.getAllCallTracking());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CallTrackingResponse> getCallTrackingById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(callTrackingService.getCallTrackingById(id));
    }

    @PostMapping
    public ResponseEntity<CallTrackingResponse> createCallTracking(@RequestBody CallTrackingRequest callTrackingRequest) {
        return ResponseEntity.ok(callTrackingService.createCallTracking(callTrackingRequest));
    }

    @PutMapping
    public ResponseEntity<CallTrackingResponse> updateCallTracking(@RequestBody CallTrackingRequest callTrackingRequest) {
        return ResponseEntity.ok(callTrackingService.updateCallTracking(callTrackingRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCallTracking(@PathVariable("id") Long id) {
        callTrackingService.deleteCallTracking(id);
        return ResponseEntity.ok("Call record deleted with ID: " + id);
    }
}
