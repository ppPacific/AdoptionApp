package com.bear.onestop.controllers;


import com.bear.onestop.data.CreateDogRequest;
import com.bear.onestop.data.dtos.*;
import com.bear.onestop.data.entities.Dog;
import com.bear.onestop.mappers.DogMapper;
import com.bear.onestop.repositories.DogRepository;
import com.bear.onestop.services.DogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/dogs")
@RequiredArgsConstructor
public class DogController {

    private final DogMapper dogMapper;
    private final DogService dogService;
    private final DogRepository dogRepository;

    @PostMapping
    public ResponseEntity<CreateDogResponseDto> createDog(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateDogRequestDto createDogRequestDto
    ) {
        CreateDogRequest createDogRequest = dogMapper.fromDto(createDogRequestDto);
        String userId = jwt.getSubject();
        Dog createdDog = dogService.createDog(userId, createDogRequest);
        CreateDogResponseDto createDogResponseDto = dogMapper.toDto(createdDog);

        return new ResponseEntity<>(createDogResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ListDogResponseDto>> listDogs(
            Pageable pageable
    ){
        Page<Dog> dogs = dogService.listDogs(pageable);
        return ResponseEntity.ok(
                dogs.map(dogMapper::toListDogResponseDto)
        );
    }
    @GetMapping(path="/{dogId}")
    public ResponseEntity<GetDogDetailsResponseDto> getDog(
            @PathVariable UUID dogId
    ){
        return dogService.getDogDetails(dogId)
                .map(dogMapper::toGetDogDetailsResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}

