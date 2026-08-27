package com.bear.onestop.config;

import java.util.*;
import java.util.stream.Collectors;

import com.bear.onestop.repositories.UserRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationConverter implements Converter<Jwt, JwtAuthenticationToken> {


    @Override
    public JwtAuthenticationToken convert(Jwt jwt) {

        Collection<GrantedAuthority> authorities = extractAuthorities(jwt);
        return new JwtAuthenticationToken(jwt, authorities,jwt.getSubject());
    }

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        // Retrieve the roles claim as a List of Strings
        String role = jwt.getClaimAsString("roles");

        if (role == null || role.isEmpty()) {
            return Collections.emptyList();
        }
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 2. Format to Spring Standard uppercase format (e.g., "admin" -> "ROLE_ADMIN")
        String formattedRole = "ROLE_" + role.toUpperCase();
        authorities.add(new SimpleGrantedAuthority(formattedRole));

        return authorities;
//        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
//
//        if(null == realmAccess || !realmAccess.containsKey("roles")) {
//            return Collections.emptyList();
//        }
//
//        @SuppressWarnings("unchecked")
//        List<String> roles = (List<String>)realmAccess.get("roles");
//
//        return roles.stream()
//                .filter(role -> role.startsWith("ROLE_"))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());


    }
}
