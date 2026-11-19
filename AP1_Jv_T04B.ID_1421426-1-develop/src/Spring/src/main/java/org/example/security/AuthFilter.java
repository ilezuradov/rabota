package org.example.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.example.service.AuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

public class AuthFilter extends GenericFilterBean {

    private final AuthService authService;

    public AuthFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();
        if (path.startsWith("/auth")) {
            chain.doFilter(request, response);
            return;
        }

        String header = req.getHeader("Authorization");
        UUID userId = authService.authenticate(header);

        if (userId != null) {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userId.toString(), null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authToken);
            chain.doFilter(request, response);
        } else {
            res.setStatus(401);
            res.getWriter().write("Unauthorized");
        }
    }
}