package com.bear.onestop.services.impl;

import com.bear.onestop.data.CreateEventRequest;
import com.bear.onestop.data.entities.Event;
import com.bear.onestop.data.entities.TicketType;
import com.bear.onestop.data.entities.User;
import com.bear.onestop.exception.UserNotFoundException;
import com.bear.onestop.repositories.EventRepository;
import com.bear.onestop.repositories.UserRepository;
import com.bear.onestop.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


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
}
