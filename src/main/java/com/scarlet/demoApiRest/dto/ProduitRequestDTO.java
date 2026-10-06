package com.scarlet.demoApiRest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProduitRequestDTO {

    private String nom;
    private String description;
    private Integer prix;
}
