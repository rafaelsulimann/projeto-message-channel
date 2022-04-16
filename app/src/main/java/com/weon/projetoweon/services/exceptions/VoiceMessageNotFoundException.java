package com.weon.projetoweon.services.exceptions;

public class VoiceMessageNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public VoiceMessageNotFoundException(Object id){
        super("Voice Message não encontrado. id " + id);
    }
    
}
