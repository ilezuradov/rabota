package org.example.service;

import org.example.dto.SignUpRequest;
import org.example.model.GameUser;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public UUID authenticate(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            return null;
        }
        String base64Credentials = authHeader.substring("Basic ".length());
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));
        String[] values = credentials.split(":", 2);
        if (values.length != 2) return null;
        String login = values[0];
        String password = values[1];

        Optional<GameUser> userOpt = userRepository.findByLogin(login);
        if (userOpt.isPresent() && encoder.matches(password, userOpt.get().getPassword())) {
            return userOpt.get().getId();
        }
        return null;
    }
}
