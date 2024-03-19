package com.jbunce.fifth_essence_backend.web.dtos.request;

import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.jbunce.fifth_essence_backend.persistance.entities.Role}
 */
public record RoleRequest(
        @Size(max = 45) String name
) implements Serializable { }