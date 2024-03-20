package com.jbunce.fifth_essence_backend.persistance.entities;

import com.jbunce.fifth_essence_backend.persistance.entities.pivots.PublicacionResource;
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

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "publications", schema = "fifth_db")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publicacion_id", nullable = false)
    private Long id;

    @Size(max = 45)
    @NotNull
    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Size(max = 255)
    @NotNull
    @Column(name = "body", nullable = false)
    private String body;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "users_user_id", nullable = false)
    private User usersUser;

    @OneToMany(mappedBy = "publicationsPublicacion")
    private Set<PublicacionResource> publicacionResources = new LinkedHashSet<>();

    @OneToMany(mappedBy = "publicationsPublicacion")
    private Set<PublicationComment> publicationComments = new LinkedHashSet<>();

}