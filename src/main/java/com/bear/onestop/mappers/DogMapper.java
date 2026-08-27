package com.bear.onestop.mappers;

import com.bear.onestop.data.CreateDogRequest;
import com.bear.onestop.data.dtos.*;
import com.bear.onestop.data.entities.Dog;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DogMapper {

    //CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);
    CreateDogRequest fromDto (CreateDogRequestDto createDogRequestDto);
    CreateDogResponseDto toDto(Dog dog);


}
