package com.jbunce.fifth_essence_backend.web.dtos.response;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.jbunce.fifth_essence_backend.persistance.entities.User}
 */
public record UserResponse(
        Long id,
        String username,
        String phoneNumber,
        String email
) implements Serializable { }