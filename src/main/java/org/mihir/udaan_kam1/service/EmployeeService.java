package org.mihir.udaan_kam1.service;

import org.mihir.udaan_kam1.model.Employee;

import java.util.Map;
import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> getEmployeeById(Long id);
    Employee createEmployee(Map<String, String> employeeMap);
    Employee updateEmployee(Map<String, String> employeeMap);
    void deleteEmployee(Long id);
}
