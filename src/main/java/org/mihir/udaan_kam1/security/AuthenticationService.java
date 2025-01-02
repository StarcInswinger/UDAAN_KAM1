package org.mihir.udaan_kam1.security;

import lombok.RequiredArgsConstructor;
import org.mihir.udaan_kam1.dao.EmployeeRepository;
import org.mihir.udaan_kam1.dto.Auth.AuthRequest;
import org.mihir.udaan_kam1.dto.Auth.AuthResponse;
import org.mihir.udaan_kam1.dto.Employee.EmployeeRequest;
import org.mihir.udaan_kam1.dto.Employee.EmployeeResponse;
import org.mihir.udaan_kam1.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthenticationService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
    }

    public AuthResponse register(EmployeeRequest employeeRequest) {
        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        EmployeeResponse employeeResponse = modelMapper.map(savedEmployee, EmployeeResponse.class);
        var jwtToken = jwtService.generateToken(employee);
        return AuthResponse.builder()
                .token(jwtToken)
                .employee(employeeResponse)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var employee = employeeRepository.findByUsername(request.getUsername()).orElseThrow();
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
        var jwtToken = jwtService.generateToken(employee);
        return AuthResponse.builder()
                .token(jwtToken)
                .employee(employeeResponse)
                .build();
    }
}

