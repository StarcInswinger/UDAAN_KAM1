package org.mihir.udaan_kam1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mihir.udaan_kam1.enums.EmployeeRole;

import java.time.ZoneId;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long employeeId;

    @Column(name = "name", nullable = false)
    String employeeName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private EmployeeRole employeeRole;

    @Column(name = "zone", nullable = false)
    private String timeZone;

    public void setTimeZone(ZoneId zoneId) {
        this.timeZone = zoneId.getId();
    }

    public ZoneId getTimeZone() {
        return ZoneId.of(this.timeZone);
    }
}
