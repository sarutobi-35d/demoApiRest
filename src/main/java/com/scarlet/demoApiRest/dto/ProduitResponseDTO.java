package com.scarlet.demoApiRest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProduitResponseDTO {

    private Long id;
    private String nom;
    private String description;
    private Integer prix;

}
