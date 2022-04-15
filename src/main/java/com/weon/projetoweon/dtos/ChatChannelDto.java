package com.weon.projetoweon.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ChatChannelDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(min = 4, max = 50)
    @NotBlank
    private String toUser;

    public ChatChannelDto(){
    }

    public ChatChannelDto(String toUser) {
        this.toUser = toUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }   
    
}
