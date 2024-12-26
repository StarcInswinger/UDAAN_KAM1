package org.mihir.udaan_kam1.dto.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mihir.udaan_kam1.enums.EmployeeRole;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private Long employeeId;
    private String employeeName;
    private String username;
    private EmployeeRole employeeRole;
    private String employeeTimeZone;
}
