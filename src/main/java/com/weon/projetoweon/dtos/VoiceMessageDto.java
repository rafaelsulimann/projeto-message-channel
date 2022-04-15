package com.weon.projetoweon.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class VoiceMessageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String message;

    public VoiceMessageDto(){
    }

    public VoiceMessageDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    
}
