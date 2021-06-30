package com.sena.inventory.jwt;

public class JwtResponse {
    private final String jwttoken;

    public JwtResponse(String token) {
        this.jwttoken = token;
    }

    public String getJwttoken() {
        return jwttoken;
    }
}
