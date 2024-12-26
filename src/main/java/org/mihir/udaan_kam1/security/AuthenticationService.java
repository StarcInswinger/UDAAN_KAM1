package org.mihir.udaan_kam1.security;

import lombok.RequiredArgsConstructor;
import org.mihir.udaan_kam1.dao.EmployeeRepository;
import org.mihir.udaan_kam1.dto.Auth.AuthRequest;
import org.mihir.udaan_kam1.dto.Auth.AuthResponse;
import org.mihir.udaan_kam1.dto.Employee.EmployeeRequest;
import org.mihir.udaan_kam1.model.Employee;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(EmployeeRequest employeeRequest) {
        var employee = Employee.builder()
                .employeeName(employeeRequest.getEmployeeName())
                .username(employeeRequest.getUsername())
                .password(passwordEncoder.encode(employeeRequest.getPassword()))
                .employeeRole(employeeRequest.getRole())
                .employeeTimeZone(employeeRequest.getTimeZone())
                .build();

        employeeRepository.save(employee);
        var jwtToken = jwtService.generateToken(employee);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var employee = employeeRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(employee);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}

