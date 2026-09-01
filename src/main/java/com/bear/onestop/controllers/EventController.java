package com.bear.onestop.controllers;



import com.bear.onestop.data.CreateEventRequest;
import com.bear.onestop.data.UpdateEventRequest;
import com.bear.onestop.data.dtos.CreateEventRequestDto;
import com.bear.onestop.data.dtos.CreateEventResponseDto;

import com.bear.onestop.data.dtos.GetEventDetailsResponseDto;
import com.bear.onestop.data.dtos.ListEventResponseDto;
import com.bear.onestop.data.entities.Event;
import com.bear.onestop.mappers.EventMapper;
import com.bear.onestop.services.EventService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bear.onestop.util.JwtUtil.parseUserId;

@RestController
@RequestMapping(path = "/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventMapper eventMapper;
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<CreateEventResponseDto> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateEventRequestDto createEventRequestDto) {
        CreateEventRequest createEventRequest = eventMapper.fromDto(createEventRequestDto);
        String userId = jwt.getSubject();
        Event createdEvent = eventService.createEvent(userId, createEventRequest);
        CreateEventResponseDto createEventResponseDto = eventMapper.toDto(createdEvent);
        return new ResponseEntity<>(createEventResponseDto, HttpStatus.CREATED);
    }

//    @PutMapping(path = "/{eventId}")
//    public ResponseEntity<UpdateEventResponseDto> updateEvent(
//            @AuthenticationPrincipal Jwt jwt,
//            @PathVariable UUID eventId,
//            @Valid @RequestBody UpdateEventRequestDto updateEventRequestDto) {
//        UpdateEventRequest updateEventRequest = eventMapper.fromDto(updateEventRequestDto);
//        String userId = parseUserId(jwt);
//
//        Event updatedEvent = eventService.updateEventForOrganizer(
//                userId, eventId, updateEventRequest
//        );
//
//        UpdateEventResponseDto updateEventResponseDto = eventMapper.toUpdateEventResponseDto(
//                updatedEvent);
//
//        return ResponseEntity.ok(updateEventResponseDto);
//    }
//
    @GetMapping
    public ResponseEntity<Page<ListEventResponseDto>> listEvents(
            @AuthenticationPrincipal Jwt jwt, Pageable pageable
    ) {
        String userId = parseUserId(jwt);
        //call map on page obj,not stream map
        Page<Event> events = eventService.listEventsForChiefstaff(userId, pageable);
        return ResponseEntity.ok(
                events.map(eventMapper::toListEventResponseDto)
        );
    }

    @GetMapping(path = "/{eventId}")
    public ResponseEntity<GetEventDetailsResponseDto> getEvent(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID eventId
    ) {
        String userId = parseUserId(jwt);
        return eventService.getEventForChiefstaff(userId, eventId)
                .map(eventMapper::toGetEventDetailsResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{eventId}")
    public ResponseEntity<Void> deleteEvent(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID eventId
    ) {
        String userId = parseUserId(jwt);
        eventService.deleteEventForChiefstaff(userId, eventId);
        return ResponseEntity.noContent().build();
    }
}
