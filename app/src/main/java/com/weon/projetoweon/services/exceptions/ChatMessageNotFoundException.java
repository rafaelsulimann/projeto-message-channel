package com.weon.projetoweon.services.exceptions;

public class ChatMessageNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ChatMessageNotFoundException(Object id){
        super("Chat Message não encontrado. id " + id);
    }
    
}
