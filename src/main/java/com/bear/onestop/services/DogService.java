package com.bear.onestop.services;

import com.bear.onestop.data.CreateDogRequest;
import com.bear.onestop.data.entities.Dog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface DogService {
    Dog createDog(String chiefstaffId, CreateDogRequest dog);
    Page<Dog> listDogs(Pageable pageable);
    Optional<Dog> getDogDetails(UUID id);
}
