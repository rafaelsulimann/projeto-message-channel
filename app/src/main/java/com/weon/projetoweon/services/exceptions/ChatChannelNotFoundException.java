package com.weon.projetoweon.services.exceptions;

public class ChatChannelNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public ChatChannelNotFoundException(Object id){
        super("Chat Channel não encontrado. id " + id);
    }
}
