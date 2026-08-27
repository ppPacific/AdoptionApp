package com.bear.onestop.data.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "dogs",
        indexes = {
                // Index the slug field for ultra-fast database lookups on the client-side
                @Index(name = "idx_dog_slug", columnList = "slug", unique = true)
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dog {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "slug", nullable = false)
    private String slug;
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "age", nullable = false)
    private String age;

    @Column(name = "size", nullable = false)
    private String size;

    @Column(name = "breed", nullable = false)
    private String breed;

    @Column(name = "sex", nullable = false)
    private String sex;

    @OneToMany(mappedBy = "dog",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DogImageDetail> images = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "dog_tags",
            joinColumns = @JoinColumn(name = "dog_id")
    )
    @Column(name = "tag")
    @Enumerated(EnumType.STRING)
    @Builder.Default // Prevents Lombok builder from defaulting this list to null
    private List<DogTraitEnum> featureTag = new ArrayList<>();

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DogStatusEnum status;

    @Column(name = "kennelLocation", nullable = false)
    private String kennelLocation;

    @Column(name = "isPublished", nullable = false)
    private Boolean isPublished;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // --- Core Slug Logic ---
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.slug = generateSlug(this.name);
    }

    @PreUpdate
    protected void onUpdate() {
        this.slug = generateSlug(this.name);
    }

    private String generateSlug(String input) {
        if (input == null || input.isBlank()) {
            return "";
        }
        return input.toLowerCase(Locale.ENGLISH)
                .replaceAll("[^a-z0-9\\s-]", "") // Remove all non-alphanumeric chars except spaces and hyphens
                .replaceAll("\\s+", "-")         // Replace spaces with single hyphens
                .replaceAll("-+", "-")           // Collapse multiple consecutive hyphens into one
                .replaceAll("^-|-$", "");        // Trim leading and trailing hyphens
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return Objects.equals(id, dog.id) && Objects.equals(name, dog.name) && Objects.equals(slug, dog.slug) && Objects.equals(description, dog.description) && Objects.equals(age, dog.age) && Objects.equals(size, dog.size) && Objects.equals(breed, dog.breed) && Objects.equals(sex, dog.sex) && status == dog.status && Objects.equals(kennelLocation, dog.kennelLocation) && Objects.equals(isPublished, dog.isPublished) && Objects.equals(createdAt, dog.createdAt) && Objects.equals(updatedAt, dog.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, slug, description, age, size, breed, sex, status, kennelLocation, isPublished, createdAt, updatedAt);
    }
}
