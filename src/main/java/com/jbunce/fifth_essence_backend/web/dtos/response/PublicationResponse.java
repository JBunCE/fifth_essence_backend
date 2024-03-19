package com.jbunce.fifth_essence_backend.web.dtos.response;

import com.jbunce.fifth_essence_backend.web.dtos.response.UserResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.jbunce.fifth_essence_backend.persistance.entities.Publication}
 */
public record PublicationResponse(
        Long id,
        UserResponse author,
        @NotNull @Size(max = 45) String title,
        @NotNull @Size(max = 255) String body
) implements Serializable { }