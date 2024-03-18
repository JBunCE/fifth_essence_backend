package com.jbunce.fifth_essence_backend.persistance.entities;

import com.jbunce.fifth_essence_backend.persistance.entities.pivots.EventResource;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "events", schema = "fifth_db")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    private Long id;

    @Column(name = "date")
    private Instant date;

    @Size(max = 45)
    @Column(name = "title", length = 45)
    private String title;

    @Size(max = 45)
    @Column(name = "description", length = 45)
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_user_id", nullable = false)
    private User usersUser;

    @OneToMany(mappedBy = "eventsEvent")
    private Set<EventResource> eventResources = new LinkedHashSet<>();

}