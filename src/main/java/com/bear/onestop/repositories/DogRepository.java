package com.bear.onestop.repositories;

import com.bear.onestop.data.entities.Dog;
import com.bear.onestop.data.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface DogRepository extends JpaRepository<Dog, UUID> {
    Page<Dog> findById(UUID dogId, Pageable pageable);
}
