package com.bear.onestop.services;

import com.bear.onestop.data.CreateEventRequest;
import com.bear.onestop.data.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;


public interface EventService {

    Event createEvent(String chiefstaffId, CreateEventRequest event);
    Page<Event> listEventsForChiefstaff(UUID staffId, Pageable pageable);
    Optional<Event> getEventForChiefstaff(UUID staffId, UUID id);

    //Event updateEventForOrganizer(UUID organizerId, UUID id, UpdateEventRequest event);

    void deleteEventForChiefstaff(UUID organizerId, UUID id);

    Page<Event> listPublishedEvents(Pageable pageable);

    Page<Event> searchPublishedEvents(String query, Pageable pageable);

    Optional<Event> getPublishedEvent(UUID id);
}
