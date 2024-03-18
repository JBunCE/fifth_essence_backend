package com.jbunce.fifth_essence_backend.persistance.entities.pivots;

import com.jbunce.fifth_essence_backend.persistance.entities.Publication;
import com.jbunce.fifth_essence_backend.persistance.entities.Resource;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "publicacion_resources", schema = "fifth_db")
public class PublicacionResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publication_resource_id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "resource_type")
    private String resourceType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publications_publicacion_id", nullable = false)
    private Publication publicationsPublicacion;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resources_resource_id", nullable = false)
    private Resource resourcesResource;

}