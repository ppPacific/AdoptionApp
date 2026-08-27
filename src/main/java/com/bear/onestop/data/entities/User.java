package com.bear.onestop.data.entities;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // Your internal database primary key

    @Column(name = "clerk_id", updatable = false, nullable = false)
    private String clerkId; // Binds directly to Clerk's user identifier (e.g., user_2Npx...)

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRoleEnum role;

    @OneToMany(mappedBy = "chiefstaff", cascade = CascadeType.ALL)//if del chiefstaff,relevant evnt also del
    private List<Event> organizedEvents = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_attending_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> attendingEvents = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_staffing_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> staffingEvents = new ArrayList<>();

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(clerkId, user.clerkId) && Objects.equals(name, user.name) && Objects.equals(email,
                user.email) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt,
                user.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clerkId, name, email, createdAt, updatedAt);
    }
}
