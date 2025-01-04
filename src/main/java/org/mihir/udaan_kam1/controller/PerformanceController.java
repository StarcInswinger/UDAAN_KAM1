package org.mihir.udaan_kam1.controller;

import org.mihir.udaan_kam1.dto.Performance.PerformanceRequest;
import org.mihir.udaan_kam1.dto.Performance.PerformanceResponse;
import org.mihir.udaan_kam1.service.Performance.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/performances")
public class PerformanceController {

    private final PerformanceService performanceService;

    @Autowired
    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping
    public ResponseEntity<List<PerformanceResponse>> getAllPerformances() {
        return ResponseEntity.ok(performanceService.getAllPerformances());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<PerformanceResponse> getPerformanceById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(performanceService.getPerformanceById(id));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PostMapping
    public ResponseEntity<PerformanceResponse> createPerformance(@RequestBody PerformanceRequest performanceRequest) {
        return ResponseEntity.ok(performanceService.createPerformance(performanceRequest));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping
    public ResponseEntity<PerformanceResponse> updatePerformance(@RequestBody PerformanceRequest performanceRequest) {
        return ResponseEntity.ok(performanceService.updatePerformance(performanceRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerformance(@PathVariable("id") Long id) {
        performanceService.deletePerformance(id);
        return ResponseEntity.ok("Performance deleted with ID: " + id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/by-restaurant")
    public ResponseEntity<PerformanceResponse> getPerformanceByRestaurantId(@RequestParam(name = "id") Long restaurantId) {
        return ResponseEntity.ok(performanceService.getPerformanceByRestaurantId(restaurantId));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/by-employee/{username}/high")
    public ResponseEntity<List<PerformanceResponse>> fetchHighPerformingAccountsByEmployeeUsername(@PathVariable(name = "username") String username){
        return ResponseEntity.ok(performanceService.fetchHighPerformingAccountsByEmployeeUsername(username));

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/by-employee/{username}/low")
    public ResponseEntity<List<PerformanceResponse>> fetchLowPerformingAccountsByEmployeeUsername(@PathVariable(name = "username") String username){
        return ResponseEntity.ok(performanceService.fetchLowPerformingAccountsByEmployeeUsername(username));
    }
}
