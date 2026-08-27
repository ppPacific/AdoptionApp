package com.bear.onestop.data.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "dog_image_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DogImageDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "imgSrc")
    private String imgsrc;
    @Column(name = "altText")
    private String alttext;
    @ManyToOne
    @JoinColumn(name = "dog_id")
    @JsonIgnore
    private Dog dog;
    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


}
