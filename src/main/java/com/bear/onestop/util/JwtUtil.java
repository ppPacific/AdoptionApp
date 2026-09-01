package com.bear.onestop.util;

import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public final class JwtUtil {
    private JwtUtil(){
    }

    public static String parseUserId(Jwt jwt) {
        return jwt.getSubject();
    }


}
