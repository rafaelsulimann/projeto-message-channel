package com.weon.projetoweon.services.exceptions;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Object id){
        super("Usuário não encontrado. id " + id);
    }
    
}
