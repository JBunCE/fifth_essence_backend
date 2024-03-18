package com.jbunce.fifth_essence_backend.persistance.entities;

import com.jbunce.fifth_essence_backend.persistance.entities.pivots.EventResource;
import com.jbunce.fifth_essence_backend.persistance.entities.pivots.PublicacionResource;
import com.jbunce.fifth_essence_backend.persistance.entities.pivots.UserResource;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "resources", schema = "fifth_db")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id", nullable = false)
    private Long id;

    @Size(max = 45)
    @NotNull
    @Column(name = "resource_url", nullable = false, length = 45)
    private String resourceUrl;

    @OneToMany(mappedBy = "resourcesResource")
    private Set<EventResource> eventResources = new LinkedHashSet<>();

    @OneToMany(mappedBy = "resourcesResource")
    private Set<PublicacionResource> publicacionResources = new LinkedHashSet<>();

    @OneToMany(mappedBy = "resourcesResource")
    private Set<UserResource> userResources = new LinkedHashSet<>();

}