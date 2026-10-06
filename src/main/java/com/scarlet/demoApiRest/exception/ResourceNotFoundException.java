package com.scarlet.demoApiRest.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message){
        super(message);
    }
}

/* CLASSE D'EXCEPTION POUR :
   Ressource introuvable (404 Not Found)*/