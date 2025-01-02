package org.mihir.udaan_kam1.dto.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mihir.udaan_kam1.dto.Employee.EmployeeResponse;
import org.mihir.udaan_kam1.model.Employee;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private EmployeeResponse employee;
}
