package com.scarlet.demoApiRest.dto;

public record AuthResponse(
        String accessToken,
        String tokenType

) {}
