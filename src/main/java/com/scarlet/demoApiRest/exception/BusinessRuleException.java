package com.scarlet.demoApiRest.exception;

public class BusinessRuleException extends RuntimeException {

    public BusinessRuleException (String message){
        super(message);
    }
}

/* CLASSE D'EXCEPTION POUR GÉRER :
Conflit / Règle métier violée (409 Conflict ou 400 Bad Request) */
