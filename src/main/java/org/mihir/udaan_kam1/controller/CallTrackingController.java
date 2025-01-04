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

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping
    public ResponseEntity<List<CallTrackingResponse>> getAllCallTrackings() {
        return ResponseEntity.ok(callTrackingService.getAllCallTracking());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<CallTrackingResponse> getCallTrackingById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(callTrackingService.getCallTrackingById(id));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PostMapping
    public ResponseEntity<CallTrackingResponse> createCallTracking(@RequestBody CallTrackingRequest callTrackingRequest) {
        return ResponseEntity.ok(callTrackingService.createCallTracking(callTrackingRequest));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<CallTrackingResponse> updateCallTracking(@PathVariable(name = "id") Long callId, @RequestBody CallTrackingRequest callTrackingRequest) {
        return ResponseEntity.ok(callTrackingService.updateCallTracking(callId, callTrackingRequest));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCallTracking(@PathVariable("id") Long id) {
        callTrackingService.deleteCallTracking(id);
        return ResponseEntity.ok("Call record deleted with ID: " + id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/last/by-poc/{pocId}")
    public ResponseEntity<CallTrackingResponse> getLastCallTrackingByPocId(@PathVariable(name = "pocId") Long pocId) {
        return ResponseEntity.ok(callTrackingService.getLastCallTrackingByPOCId(pocId));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/by-poc/{pocId}")
    public ResponseEntity<List<CallTrackingResponse>> getAllCallTrackingByPocId(@PathVariable(name = "pocId") Long pocId) {
        return ResponseEntity.ok(callTrackingService.getAllCallTrackingsByPOCId(pocId));
    }
}
