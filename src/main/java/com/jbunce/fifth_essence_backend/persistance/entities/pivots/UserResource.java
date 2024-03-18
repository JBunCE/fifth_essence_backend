package com.jbunce.fifth_essence_backend.persistance.entities.pivots;

import com.jbunce.fifth_essence_backend.persistance.entities.Resource;
import com.jbunce.fifth_essence_backend.persistance.entities.User;
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
@Table(name = "user_resources", schema = "fifth_db")
public class UserResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_resource_id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "resource_type")
    private String resourceType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resources_resource_id", nullable = false)
    private Resource resourcesResource;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_user_id", nullable = false)
    private User usersUser;

}