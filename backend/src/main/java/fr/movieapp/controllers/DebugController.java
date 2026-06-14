package fr.movieapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/debug")
public class DebugController {

    @Value("${application.cors.allowed-origins}")
    private String allowedOrigins;

    @GetMapping("/cors")
    public ResponseEntity<String> corsValue() {
        return ResponseEntity.ok()
            .body("allowedOrigins = [" + allowedOrigins + "]");
    }
}
