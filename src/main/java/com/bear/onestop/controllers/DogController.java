package com.bear.onestop.controllers;


import com.bear.onestop.data.CreateDogRequest;
import com.bear.onestop.data.dtos.CreateDogRequestDto;
import com.bear.onestop.data.dtos.CreateDogResponseDto;
import com.bear.onestop.data.dtos.CreateEventRequestDto;
import com.bear.onestop.data.dtos.CreateEventResponseDto;
import com.bear.onestop.data.entities.Dog;
import com.bear.onestop.mappers.DogMapper;
import com.bear.onestop.services.DogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/dogs")
@RequiredArgsConstructor
public class DogController {

    private final DogMapper dogMapper;
    private final DogService dogService;

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
}

