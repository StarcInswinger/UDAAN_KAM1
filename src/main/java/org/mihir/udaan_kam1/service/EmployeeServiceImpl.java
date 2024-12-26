package org.mihir.udaan_kam1.service;

import org.mihir.udaan_kam1.dao.EmployeeRepository;
import org.mihir.udaan_kam1.dto.EmployeeRequest;
import org.mihir.udaan_kam1.dto.EmployeeResponse;
import org.mihir.udaan_kam1.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponse> employeeResponses = new ArrayList<>();

        for (Employee employee : employees) {
            employeeResponses.add(modelMapper.map(employee, EmployeeResponse.class));
        }
        return employeeResponses;
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        return modelMapper.map(employee, EmployeeResponse.class);
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        return modelMapper.map(savedEmployee, EmployeeResponse.class);
    }

    @Override
    public EmployeeResponse updateEmployee(EmployeeRequest employeeRequest) {
        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        return modelMapper.map(savedEmployee, EmployeeResponse.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
