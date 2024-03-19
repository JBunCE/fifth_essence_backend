package com.jbunce.fifth_essence_backend.web.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.jbunce.fifth_essence_backend.persistance.entities.Publication}
 */
public record PublicationRequest(
        @NotNull Long authorId,
        @NotNull @Size(max = 45) String title,
        @NotNull @Size(max = 255) String body
) implements Serializable { }