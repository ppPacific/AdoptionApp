package com.bear.onestop.data.dtos;

import com.bear.onestop.data.entities.EventStatusEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

//USED AT PRESENTATION LAYER
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequestDto {

    @NotBlank(message = "Event name is required")
    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    @NotBlank(message = "Venue information is required")
    private String venue;
    private LocalDateTime salesStart;
    private LocalDateTime salesEnd;
    private String description;
    private String overview;
    private String slug;
    private String imageUrl;
    private String location;
    private String mode;
    private String audience;
    private String eventOwner;
    private List<String> tags;
    @NotNull(message = "Event status must be provided")
    private EventStatusEnum status;
    @NotEmpty(message = "At least one ticket type is required")
    @Valid
    private List<CreateTicketTypeRequestDto> ticketTypes;
}
