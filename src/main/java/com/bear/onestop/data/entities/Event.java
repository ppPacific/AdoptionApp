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
@Table(name = "events",
        indexes = {
                // Index the slug field for ultra-fast database lookups on the client-side
                @Index(name = "idx_event_slug", columnList = "slug", unique = true)
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "event_start")
    private LocalDateTime start;

    @Column(name = "event_end")
    private LocalDateTime end;

    @Column(name = "venue", nullable = false)
    private String venue;

    @Column(name = "sales_start")
    private LocalDateTime salesStart;

    @Column(name = "sales_end")
    private LocalDateTime salesEnd;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatusEnum status;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "overview", nullable = false)
    private String overview;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "imageUrl", nullable = false)
    private String imageUrl;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "mode", nullable = false)
    private String mode;

    @Column(name = "audience", nullable = false)
    private String audience;

    @Column(name = "eventOwner", nullable = false)
    private String eventOwner;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "event_tags",
            joinColumns = @JoinColumn(name = "event_id")
    )
    @Column(name = "tag")
    @Builder.Default // Prevents Lombok builder from defaulting this list to null
    private List<String> tags = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY) //n + 1 problem
    @JoinColumn(name = "organizer_id")
    private User chiefstaff;

    @ManyToMany(mappedBy = "attendingEvents")
    private List<User> attendees = new ArrayList<>();

    @ManyToMany(mappedBy = "staffingEvents")
    private List<User> staff = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketType> ticketTypes = new ArrayList<>();

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
        this.slug = generateSlug(this.title);
    }

    @PreUpdate
    protected void onUpdate() {
        this.slug = generateSlug(this.title);
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(title, event.title) && Objects.equals(start,
                event.start) && Objects.equals(end, event.end) && Objects.equals(venue, event.venue)
                && Objects.equals(salesStart, event.salesStart) && Objects.equals(salesEnd, event.salesEnd)
                && status == event.status && Objects.equals(createdAt, event.createdAt) && Objects.equals(
                updatedAt, event.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, start, end, venue, salesStart, salesEnd, status, createdAt,
                updatedAt);
    }
}
