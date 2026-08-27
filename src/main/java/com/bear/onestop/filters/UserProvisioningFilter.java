package com.bear.onestop.filters;

import com.bear.onestop.data.entities.User;
import com.bear.onestop.data.entities.UserRoleEnum;
import com.bear.onestop.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class UserProvisioningFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null
                && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof Jwt jwt) {

            // Clerk uses alpha-numeric strings (e.g., 'user_2Npx...') instead of standard UUID format
            String clerkId = jwt.getSubject();
            // Check existence using the direct Clerk ID string lookup
            if (!userRepository.existsByClerkId(clerkId)) {

                User user = new User();
                user.setClerkId(clerkId); // Ensure your User domain entity class updates its ID column to a String type

                // Clerk places the user ID directly in the 'sub' claim.
                // If you mapped custom profile details into your Clerk session settings, extract them here:
                String displayName = jwt.getClaimAsString("lastname");
                user.setName(displayName != null ? displayName : "Clerk User");

                // Pass standard sub or email address mapping strings down to database state row
                user.setEmail(jwt.getClaimAsString("email") != null ? displayName : "Clerk email");
                String clerkRole = jwt.getClaimAsString("role");
                System.out.println(" clerkrole" + clerkRole);
                if ("ADMIN".equalsIgnoreCase(clerkRole)) {
                    user.setRole(UserRoleEnum.ROLE_ADMIN);
                } else {
                    user.setRole(UserRoleEnum.ROLE_USER); // Default fallback
                }
                userRepository.save(user);
            }
        }

        filterChain.doFilter(request, response);
    }
}

