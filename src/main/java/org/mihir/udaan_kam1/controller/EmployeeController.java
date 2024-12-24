package org.mihir.udaan_kam1.controller;

import org.mihir.udaan_kam1.model.Employee;
import org.mihir.udaan_kam1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<String> getEmployee(@RequestParam Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id).toString());
    }

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody Map<String, String> employeeMap) {
        Employee createdEmployee = employeeService.createEmployee(employeeMap);
        return ResponseEntity.ok("Employee Created with Employee ID: " + createdEmployee.getEmployeeId());
    }

    @PutMapping
    public ResponseEntity<String> updateEmployee(@RequestBody Map<String, String> employeeMap) {
        Employee updatedEmployee = employeeService.updateEmployee(employeeMap);
        return ResponseEntity.ok("Employee Updated with Employee ID: " + updatedEmployee.getEmployeeId());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(@RequestParam Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee Deleted");
    }
}
