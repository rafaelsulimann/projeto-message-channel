package com.weon.projetoweon.services.exceptions;

public class EmailChannelNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public EmailChannelNotFoundException(Object id){
        super("Email Channel não encontrado. id " + id);
    }
}
