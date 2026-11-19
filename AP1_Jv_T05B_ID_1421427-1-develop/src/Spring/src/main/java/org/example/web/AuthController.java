package org.example.web;

import org.example.dto.SignUpRequest;
import org.example.model.JwtRequest;
import org.example.model.JwtResponse;
import org.example.model.RefreshJwtRequest;
import org.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest request) {
        boolean result = authService.register(request);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Registered");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Login already exists");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refreshAccessToken(@RequestBody RefreshJwtRequest request) {
        return ResponseEntity.ok(authService.refreshAccessToken(request.getRefreshToken()));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtResponse> refreshRefreshToken(@RequestBody RefreshJwtRequest request) {
        return ResponseEntity.ok(authService.refreshRefreshToken(request.getRefreshToken()));
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> getCurrentUser(Authentication auth) {
        return ResponseEntity.ok(Map.of("userId", auth.getName()));
    }
}
