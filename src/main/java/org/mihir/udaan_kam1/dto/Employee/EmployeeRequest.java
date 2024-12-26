package org.mihir.udaan_kam1.dto.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mihir.udaan_kam1.enums.EmployeeRole;

import java.time.ZoneId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    private String employeeName;
    private String username;
    private String password;
    private EmployeeRole role;
    private ZoneId timeZone;
}
