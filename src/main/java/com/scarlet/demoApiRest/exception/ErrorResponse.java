package com.scarlet.demoApiRest.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
//Exclu de la réponse JSON tous les champs qui ont une valeur null lors de la sérialisation
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    int status; //Le code HTTP numérique (ex: 400, 404, 500).
    String error; // Le nom court du statut HTTP (ex: "Bad Request", "Not Found").
    String message; // Un message explicatif global destiné au client.
    Map<String, String> validationErrors; // Dictionnaire champ -> message rempli uniquement lors d'erreurs de validation des formulaires/DTOs.
    String path; // L'URL (endpoint) sur laquelle l'erreur s'est produite.
    LocalDateTime timestamp; // La date et l'heure exactes de l'erreur.
}
