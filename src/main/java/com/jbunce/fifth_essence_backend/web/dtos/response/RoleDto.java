package com.jbunce.fifth_essence_backend.web.dtos.response;

import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.jbunce.fifth_essence_backend.persistance.entities.Role}
 */
public record RoleDto(Long id, @Size(max = 45) String name) implements Serializable {
}