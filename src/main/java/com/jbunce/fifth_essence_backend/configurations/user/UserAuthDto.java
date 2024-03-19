package com.jbunce.fifth_essence_backend.configurations.user;

import lombok.Data;

@Data
public class UserAuthDto {
    private String phoneNumber;
    private String password;
}
