package org.mihir.udaan_kam1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mihir.udaan_kam1.enums.EmployeeRole;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String employeeName;
    private String username;
    private String password;
    private EmployeeRole role;
    private String timeZone;
}
