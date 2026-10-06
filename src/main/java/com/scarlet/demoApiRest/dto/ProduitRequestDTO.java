package com.scarlet.demoApiRest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProduitRequestDTO {

    @NotBlank(message = "Veuillez insérer le nom du produit")
    @Size(min = 2, max = 20, message = "Le nom doit contenir entre 2 et 20 caractères max.")
    private String nom;

    @NotBlank(message = "Veuillez écrire la description")
    @Size(min = 3, max = 150, message ="La description doit contenir entre 3 et 150 caractères max.")
    private String description;

    @NotNull(message = "Veuillez insérer le prix")
    @Positive(message = "Le prix ne peut pas etre négatif")
    private Integer prix;
}
