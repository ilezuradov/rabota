package org.example.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.example.model.JwtAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;

public class AuthFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;
    private final JwtUtil jwtUtil;

    public AuthFilter(JwtProvider jwtProvider, JwtUtil jwtUtil) {
        this.jwtProvider = jwtProvider;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();
        if (path.startsWith("/auth/signup") || path.startsWith("/auth/login") || path.startsWith("/auth/refresh")) {
            chain.doFilter(request, response);
            return;
        }

        String header = req.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtProvider.validateAccessToken(token)) {
                Claims claims = jwtProvider.getClaims(token);
                JwtAuthentication auth = jwtUtil.createAuthentication(claims);
                SecurityContextHolder.getContext().setAuthentication(auth);
                chain.doFilter(request, response);
                return;
            }
        }
        res.setStatus(401);
        res.getWriter().write("Unauthorized");
    }
}
