package org.mihir.udaan_kam1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mihir.udaan_kam1.dao.EmployeeRepository;
import org.mihir.udaan_kam1.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ObjectMapper objectMapper) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee createEmployee(Map<String, String> employeeMap) {
        Employee employee = objectMapper.convertValue(employeeMap, Employee.class);
        return employeeRepository.saveAndFlush(employee);
    }

    public Employee updateEmployee(Map<String, String> employeeMap) {
        Employee employee = objectMapper.convertValue(employeeMap, Employee.class);
        return employeeRepository.saveAndFlush(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
