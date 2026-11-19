package org.example.web;

import org.example.dto.SignUpRequest;
import org.example.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest request) {
        boolean result = authService.register(request);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Registered");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Login already exists");
    }

    @GetMapping("/signin")
    public ResponseEntity<String> signIn(@RequestHeader("Authorization") String authHeader) {
        UUID userId = authService.authenticate(authHeader);
        if (userId != null) {
            return ResponseEntity.ok(userId.toString());
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}