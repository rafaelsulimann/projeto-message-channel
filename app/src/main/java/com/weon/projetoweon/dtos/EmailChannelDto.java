package com.weon.projetoweon.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmailChannelDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(min = 4, max = 50)
    @NotBlank
    private String toEmail;

    public EmailChannelDto(){
    }

    public EmailChannelDto(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }   
    
}
