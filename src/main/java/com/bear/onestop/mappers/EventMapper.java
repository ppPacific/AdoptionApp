package com.bear.onestop.mappers;

import com.bear.onestop.data.CreateEventRequest;
import com.bear.onestop.data.CreateTicketTypeRequest;
import com.bear.onestop.data.dtos.CreateEventRequestDto;
import com.bear.onestop.data.dtos.CreateEventResponseDto;
import com.bear.onestop.data.dtos.CreateTicketTypeRequestDto;

import com.bear.onestop.data.entities.Event;
import com.bear.onestop.data.entities.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);
    CreateEventRequest fromDto (CreateEventRequestDto createEventRequestDto);
    CreateEventResponseDto toDto(Event event);


}
