package com.bear.onestop.data.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DogImageDetailDto {
    private String imgsrc;
    private String alttext;
}
