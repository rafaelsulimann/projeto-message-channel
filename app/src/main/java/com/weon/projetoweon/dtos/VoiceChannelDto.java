package com.weon.projetoweon.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class VoiceChannelDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(min = 4, max = 50)
    @NotBlank
    private String toPhone;

    public VoiceChannelDto(){
    }

    public VoiceChannelDto(String toPhone) {
        this.toPhone = toPhone;
    }

    public String getToPhone() {
        return toPhone;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }   
    
}
