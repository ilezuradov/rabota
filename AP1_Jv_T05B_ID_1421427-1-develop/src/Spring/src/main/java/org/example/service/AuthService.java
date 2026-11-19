package org.example.service;

import org.example.dto.SignUpRequest;
import org.example.model.GameUser;
import org.example.model.JwtAuthentication;
import org.example.model.JwtRequest;
import org.example.model.JwtResponse;
import org.example.repository.UserRepository;
import org.example.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public AuthService(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public boolean register(SignUpRequest request) {
        if (userRepository.existsByLogin(request.getLogin())) {
            return false;
        }
        GameUser user = new GameUser();
        user.setLogin(request.getLogin());
        user.setPassword(encoder.encode(request.getPassword()));
        userRepository.save(user);
        return true;
    }

    public JwtResponse login(JwtRequest request) {
        Optional<GameUser> userOpt = userRepository.findByLogin(request.getLogin());
        if (userOpt.isPresent() && encoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            UUID userId = userOpt.get().getId();
            String accessToken = jwtProvider.generateAccessToken(userId, List.of("USER"));
            String refreshToken = jwtProvider.generateRefreshToken(userId);
            return new JwtResponse(accessToken, refreshToken);
        }
        throw new RuntimeException("Invalid credentials");
    }

    public JwtResponse refreshAccessToken(String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            UUID userId = UUID.fromString(jwtProvider.getClaims(refreshToken).getSubject());
            String newAccessToken = jwtProvider.generateAccessToken(userId, List.of("USER"));
            String newRefreshToken = jwtProvider.generateRefreshToken(userId);
            return new JwtResponse(newAccessToken, newRefreshToken);
        }
        throw new RuntimeException("Invalid refresh token");
    }

    public JwtResponse refreshRefreshToken(String refreshToken) {
        return refreshAccessToken(refreshToken);
    }

    public UUID getCurrentUserId() {
        return (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
