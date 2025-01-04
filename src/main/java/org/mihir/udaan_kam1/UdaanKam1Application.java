package org.mihir.udaan_kam1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableScheduling
@EnableWebMvc
public class UdaanKam1Application {

    public static void main(String[] args) {
        SpringApplication.run(UdaanKam1Application.class, args);
    }

}
