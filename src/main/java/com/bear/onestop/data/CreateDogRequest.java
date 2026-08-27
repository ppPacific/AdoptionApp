package com.bear.onestop.data;

import com.bear.onestop.data.entities.DogImageDetail;
import com.bear.onestop.data.entities.DogStatusEnum;
import com.bear.onestop.data.entities.DogTraitEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDogRequest {

    private String name;
    private String slug;
    private String description;
    private String age;
    private String size;
    private String breed;
    private String sex;
    private List<DogImageDetail> images;
    private List<DogTraitEnum> featureTag;
    private DogStatusEnum status;
    private String kennelLocation;
    private Boolean isPublished;


}
