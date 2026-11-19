package org.example.model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.UUID;

public class JwtAuthentication implements Authentication {
    private final UUID uuid;
    private final Collection<? extends GrantedAuthority> authorities;
    private boolean authenticated = true;

    public JwtAuthentication(UUID uuid, Collection<? extends GrantedAuthority> authorities) {
        this.uuid = uuid;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    @Override
    public Object getCredentials() { return null; }

    @Override
    public Object getDetails() { return null; }

    @Override
    public Object getPrincipal() { return uuid; }

    @Override
    public boolean isAuthenticated() { return authenticated; }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() { return uuid.toString(); }
}
