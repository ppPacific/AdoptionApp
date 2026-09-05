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
public class ListPublishedEventResponseDto {
    private UUID id;
    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private String venue;
    private String description;
    private String slug;
    private String location;
}
