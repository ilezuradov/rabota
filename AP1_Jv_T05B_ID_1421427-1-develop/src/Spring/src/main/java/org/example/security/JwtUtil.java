package org.example.security;

import io.jsonwebtoken.Claims;
import org.example.model.JwtAuthentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    public JwtAuthentication createAuthentication(Claims claims) {
        UUID uuid = UUID.fromString(claims.getSubject());
        List<String> roles = claims.get("roles", List.class);
        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new JwtAuthentication(uuid, authorities);
    }
}
