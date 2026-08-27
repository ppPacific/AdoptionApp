package com.bear.onestop.data;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bear.onestop.data.entities.EventStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//used at SERVICE LAYER
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequest {

    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
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
    private EventStatusEnum status;
    //CHANGED TO lowercase for correct mapping
    private List<CreateTicketTypeRequest> ticketTypes = new ArrayList<>();
    //private User chiefstaff;
}
