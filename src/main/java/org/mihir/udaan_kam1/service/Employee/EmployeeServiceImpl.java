package org.mihir.udaan_kam1.service.Employee;

import org.mihir.udaan_kam1.dao.EmployeeRepository;
import org.mihir.udaan_kam1.dto.Employee.EmployeeRequest;
import org.mihir.udaan_kam1.dto.Employee.EmployeeResponse;
import org.mihir.udaan_kam1.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

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
    public EmployeeResponse updateEmployee(Long employeeId, EmployeeRequest employeeRequest) {
        Employee oldEmployee = employeeRepository.findById(employeeId).get();
        if (!employeeRequest.getEmployeeName().equals(oldEmployee.getEmployeeName())) {
            oldEmployee.setEmployeeName(employeeRequest.getEmployeeName());
        }
        if (!employeeRequest.getUsername().equals(oldEmployee.getUsername())) {
            oldEmployee.setUsername(employeeRequest.getUsername());
        }
        if (!employeeRequest.getPassword().equals(oldEmployee.getPassword())) {
            oldEmployee.setPassword(employeeRequest.getPassword());
        }
        if (!employeeRequest.getRole().equals(oldEmployee.getEmployeeRole())) {
            oldEmployee.setEmployeeRole(employeeRequest.getRole());
        }
        if (!employeeRequest.getTimeZone().equals(oldEmployee.getEmployeeTimeZone())) {
            oldEmployee.setEmployeeTimeZone(employeeRequest.getTimeZone());
        }
        Employee savedEmployee = employeeRepository.saveAndFlush(oldEmployee);
        return modelMapper.map(savedEmployee, EmployeeResponse.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }


    @Override
    public EmployeeResponse getEmployeeByUsername(String username) {
        Employee employee = employeeRepository.findByUsername(username).get();
        return modelMapper.map(employee, EmployeeResponse.class);
    }
}
