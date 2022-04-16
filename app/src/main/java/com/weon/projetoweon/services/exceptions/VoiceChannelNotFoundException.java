package com.weon.projetoweon.services.exceptions;

public class VoiceChannelNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public VoiceChannelNotFoundException(Object id){
        super("Voice Channel não encontrado. id " + id);
    }
}
