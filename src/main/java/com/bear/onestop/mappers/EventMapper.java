package com.bear.onestop.mappers;

import com.bear.onestop.data.CreateEventRequest;
import com.bear.onestop.data.CreateTicketTypeRequest;
import com.bear.onestop.data.dtos.*;

import com.bear.onestop.data.entities.Event;
import com.bear.onestop.data.entities.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);
    CreateEventRequest fromDto (CreateEventRequestDto createEventRequestDto);
    CreateEventResponseDto toDto(Event event);

    ListEventTicketTypeResponseDto toDto(TicketType ticketType);

    ListEventResponseDto toListEventResponseDto(Event event);

    GetEventDetailsTicketTypesResponseDto toGetEventDetailsTicketTypesResponseDto(
            TicketType ticketType);

    GetEventDetailsResponseDto toGetEventDetailsResponseDto(Event event);

//    UpdateTicketTypeRequest fromDto(UpdateTicketTypeRequestDto dto);
//
//    UpdateEventRequest fromDto(UpdateEventRequestDto dto);
//
//    UpdateTicketTypeResponseDto toUpdateTicketTypeResponseDto(TicketType ticketType);
//
//    UpdateEventResponseDto toUpdateEventResponseDto(Event event);

    ListPublishedEventResponseDto toListPublishedEventResponseDto(Event event);

    GetPublishedEventDetailsTicketTypesResponseDto toGetPublishedEventDetailsTicketTypesResponseDto(
            TicketType ticketType);

    GetPublishedEventDetailsResponseDto toGetPublishedEventDetailsResponseDto(Event event);
}
