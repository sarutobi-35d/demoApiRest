package com.scarlet.demoApiRest.dto;

import com.scarlet.demoApiRest.entity.enums.Role;

public record UtilisateurResponse(

        Long id,
        String email,
        Role role

) {}
