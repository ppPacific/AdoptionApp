package com.bear.onestop.data.dtos;


import com.bear.onestop.data.entities.EventStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
//USED AT PRESENTATION LAYER
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventResponseDto {

    private UUID id;
    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private String venue;
    private LocalDateTime salesStart;
    private LocalDateTime salesEnd;
    private EventStatusEnum status;
    private List<CreateTicketTypeResponseDto> ticketTypes;
    private String description;
    private String overview;
    private String slug;
    private String imageUrl;
    private String location;
    private String mode;
    private String audience;
    private String eventOwner;
    private List<String> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
