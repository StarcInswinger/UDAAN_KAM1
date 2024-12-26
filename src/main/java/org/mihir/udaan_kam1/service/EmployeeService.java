package org.mihir.udaan_kam1.service;

import org.mihir.udaan_kam1.dto.EmployeeRequest;
import org.mihir.udaan_kam1.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse getEmployeeById(Long id);
    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);
    EmployeeResponse updateEmployee(EmployeeRequest employeeRequest);
    void deleteEmployee(Long id);
}
