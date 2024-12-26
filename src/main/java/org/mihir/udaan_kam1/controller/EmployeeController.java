package org.mihir.udaan_kam1.controller;

import org.mihir.udaan_kam1.dto.EmployeeRequest;
import org.mihir.udaan_kam1.dto.EmployeeResponse;
import org.mihir.udaan_kam1.model.Employee;
import org.mihir.udaan_kam1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getEmployee(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id).toString());
    }

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);
        return ResponseEntity.ok("Employee Created with Employee ID: " + employeeResponse.getEmployeeId());
    }

    @PutMapping
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.updateEmployee(employeeRequest);
        return ResponseEntity.ok("Employee Updated with Employee ID: " + employeeResponse.getEmployeeId());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(@RequestParam Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee Deleted");
    }
}
