package com.bear.onestop.data.dtos;


import com.bear.onestop.data.entities.EventStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListEventResponseDto {
    private UUID id;
    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private String venue;
    private LocalDateTime salesStart;
    private LocalDateTime salesEnd;
    private EventStatusEnum status;
    private List<ListEventTicketTypeResponseDto> ticketTypes = new ArrayList<>();
    private String description;
    private String overview;
    private String slug;
    private String imageUrl;
    private String location;
    private String mode;
    private String audience;
    private String eventOwner;
    private List<String> tags= new ArrayList<>();//null safety
}
