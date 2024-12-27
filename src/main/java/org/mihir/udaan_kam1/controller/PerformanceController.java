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

    @GetMapping
    public ResponseEntity<List<PerformanceResponse>> getAllPerformances() {
        return ResponseEntity.ok(performanceService.getAllPerformances());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceResponse> getPerformanceById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(performanceService.getPerformanceById(id));
    }

    @PostMapping
    public ResponseEntity<PerformanceResponse> createPerformance(@RequestBody PerformanceRequest performanceRequest) {
        return ResponseEntity.ok(performanceService.createPerformance(performanceRequest));
    }

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
}
