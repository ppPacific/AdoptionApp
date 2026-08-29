package com.bear.onestop.services.impl;

import com.bear.onestop.data.CreateEventRequest;
import com.bear.onestop.data.entities.Event;
import com.bear.onestop.data.entities.EventStatusEnum;
import com.bear.onestop.data.entities.TicketType;
import com.bear.onestop.data.entities.User;
import com.bear.onestop.exception.UserNotFoundException;
import com.bear.onestop.repositories.EventRepository;
import com.bear.onestop.repositories.UserRepository;
import com.bear.onestop.services.EventService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    @Override
    public Event createEvent(String chiefstaffClerkId, CreateEventRequest event) {
        User staff = userRepository.findByClerkId(chiefstaffClerkId)
                .orElseThrow(()-> new UserNotFoundException(
                        String.format("User with ID '%s' not found", chiefstaffClerkId)
                ));

        Event eventToCreate = new Event();
        List<TicketType> ticketTypesToCreate = event.getTicketTypes().stream().map(
                ticketType -> {
                    TicketType ticketTypeToCreate = new TicketType();
                    ticketTypeToCreate.setName(ticketType.getName());
                    ticketTypeToCreate.setPrice(ticketType.getPrice());
                    ticketTypeToCreate.setDescription(ticketType.getDescription());
                    ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
                    ticketTypeToCreate.setEvent(eventToCreate);
                    return ticketTypeToCreate;
                }).toList();

        eventToCreate.setTitle(event.getTitle());
        eventToCreate.setStart(event.getStart());
        eventToCreate.setEnd(event.getEnd());
        eventToCreate.setVenue(event.getVenue());
        eventToCreate.setSalesStart(event.getSalesStart());
        eventToCreate.setSalesEnd(event.getSalesEnd());
        eventToCreate.setStatus(event.getStatus());
        eventToCreate.setOverview(event.getOverview());
        eventToCreate.setSlug(event.getSlug());
        eventToCreate.setImageUrl(event.getImageUrl());
        eventToCreate.setLocation(event.getLocation());
        eventToCreate.setMode(event.getMode());
        eventToCreate.setAudience(event.getAudience());
        eventToCreate.setEventOwner(event.getEventOwner());
        eventToCreate.setChiefstaff(staff);
        eventToCreate.setDescription(event.getDescription());
        eventToCreate.setTags(event.getTags());
        eventToCreate.setTicketTypes(ticketTypesToCreate);

        return eventRepository.save(eventToCreate);

    }

    @Override
    public Page<Event> listEventsForChiefstaff(UUID chiefId, Pageable pageable) {
        return eventRepository.findByChiefstaffId(chiefId, pageable);
    }

    @Override
    public Optional<Event> getEventForChiefstaff(UUID chiefId, UUID id) {
        return eventRepository.findByIdAndChiefstaffId(id, chiefId);
    }

    @Override
    @Transactional
    public void deleteEventForChiefstaff(UUID organizerId, UUID id) {
        getEventForChiefstaff(organizerId, id).ifPresent(eventRepository::delete);
    }

    @Override
    public Page<Event> listPublishedEvents(Pageable pageable) {
        return eventRepository.findByStatus(EventStatusEnum.PUBLISHED, pageable);
    }

    @Override
    public Page<Event> searchPublishedEvents(String query, Pageable pageable) {
        return eventRepository.searchEvents(query, pageable);
    }

    @Override
    public Optional<Event> getPublishedEvent(UUID id) {
        return eventRepository.findByIdAndStatus(id, EventStatusEnum.PUBLISHED);
    }
}
