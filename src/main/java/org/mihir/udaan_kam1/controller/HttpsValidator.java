package org.mihir.udaan_kam1.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/.well-known/pki-validation")
public class HttpsValidator {
    @GetMapping("/5A7881ED12C3EBDE8FE0D7EE731E4F35.txt")
    public ResponseEntity<Resource> downloadFile() throws IOException {
        Resource resource = new ClassPathResource("static/.well-known/pki-validation/5A7881ED12C3EBDE8FE0D7EE731E4F35.txt");

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(resource);
    }
}
