package com.bear.onestop.data.dtos;


import com.bear.onestop.data.entities.DogImageDetail;
import com.bear.onestop.data.entities.DogStatusEnum;
import com.bear.onestop.data.entities.DogTraitEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDogRequestDto {

    @NotBlank(message = "Dog name is required")
    private String name;
    private String slug;
    private String description;
    private String age;
    private String size;
    private String breed;
    private String sex;
    private List<DogImageDetail> images;
    private List<DogTraitEnum> featureTag;
    @NotNull(message = "Dog status must be provided")
    private DogStatusEnum status;
    private String kennelLocation;
    private Boolean isPublished;
}
