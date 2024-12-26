package org.mihir.udaan_kam1.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mihir.udaan_kam1.dao.EmployeeRepository;
import org.mihir.udaan_kam1.enums.EmployeeRole;
import org.mihir.udaan_kam1.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
@Slf4j
public class AdminInitialiser implements CommandLineRunner {
    final private EmployeeRepository employeeRepository;
    final private PasswordEncoder passwordEncoder;

    @Autowired
    AdminInitialiser(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${login.admin.username}")
    private String adminUsername;

    @Value("${login.admin.password}")
    private String adminPassword;

    ZoneId currentTimeZone = ZoneId.systemDefault();

    @Override
    public void run(String... args) {
        if (employeeRepository.findByUsername(adminUsername).isEmpty()) {
            Employee adminEmployee = Employee.builder()
                    .employeeName(adminUsername)
                    .username(adminUsername)
                    .password(passwordEncoder.encode(adminPassword))
                    .employeeRole(EmployeeRole.ADMIN)
                    .employeeTimeZone(currentTimeZone)
                    .build();

            employeeRepository.save(adminEmployee);

            // Print credentials in a clear, visible format
            log.info("========================================");
            log.info("          ADMIN USER CREATED           ");
            log.info("========================================");
            log.info("Username: {}", adminUsername);
            log.info("Password: {}", adminPassword);
            log.info("========================================");
            log.info("Please change these credentials after first login!");
            log.info("========================================");
        } else {
            log.info("Admin user already exists - skipping initialization");
        }
    }
}
