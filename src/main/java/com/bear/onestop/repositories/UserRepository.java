package com.bear.onestop.repositories;


import com.bear.onestop.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    // Standard CRUD operations are inherited automatically
    boolean existsByClerkId(String clerkId);

    // Retrieves the full user record using the incoming token's subject identifier
    Optional<User> findByClerkId(String clerkId);
}
