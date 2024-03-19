package com.jbunce.fifth_essence_backend.web.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.jbunce.fifth_essence_backend.persistance.entities.User}
 */
public record UserRequest(
        @NotNull @Size(max = 45) @NotBlank String username,
        @NotNull @Size(max = 45) @NotBlank String phoneNumber,
        @Size(max = 45) String email,
        @Size(min = 8, max = 45) String password,
        Set<RoleRequest> roles
) implements Serializable { }