package org.example.web;

import org.example.model.GameUser;
import org.example.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable UUID id) {
        return userRepository.findById(id)
                .map(user -> Map.of("id", user.getId(), "login", user.getLogin()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}