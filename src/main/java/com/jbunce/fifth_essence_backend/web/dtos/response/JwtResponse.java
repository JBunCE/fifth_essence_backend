package com.jbunce.fifth_essence_backend.web.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtResponse {
    private String token;
    private String refreshToken;
}
