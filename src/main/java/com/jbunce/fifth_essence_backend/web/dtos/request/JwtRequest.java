package com.jbunce.fifth_essence_backend.web.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtRequest {

    @NotBlank
    private String refreshToken;

}
