package com.weon.projetoweon.services.exceptions;

public class EmailMessageNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailMessageNotFoundException(Object id){
        super("Email Message não encontrado. id " + id);
    }
    
}
