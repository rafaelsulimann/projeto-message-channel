package com.weon.projetoweon.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class EmailMessageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String message;

    public EmailMessageDto(){
    }

    public EmailMessageDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    
}
