package com.seyitkarahan.akilli_ajanda_api.dto.request;

import lombok.Data;

@Data
public class AuthRequest {

    private String name;
    private String email;
    private String password;
}
