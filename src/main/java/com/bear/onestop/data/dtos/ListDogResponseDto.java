package com.bear.onestop.data.dtos;


import com.bear.onestop.data.entities.DogStatusEnum;
import com.bear.onestop.data.entities.DogTraitEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListDogResponseDto {
    private UUID id;
    private String name;
    private String slug;
    private String description;
    private String age;
    private String size;
    private String breed;
    private String sex;
    private List<DogImageDetailDto> images;
    private List<DogTraitEnum> featureTag;
    private DogStatusEnum status;
    private String kennelLocation;
    private Boolean isPublished;
}
