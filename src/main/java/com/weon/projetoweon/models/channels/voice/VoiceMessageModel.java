package com.weon.projetoweon.models.channels.voice;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.weon.projetoweon.models.enums.MessageStatus;

@Entity
@Table(name = "tb_voice_messages")
public class VoiceMessageModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID voiceMessageId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "voice_channel_id")
    private VoiceChannelModel voiceChannel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageStatus messageStatus;

    @Column(nullable = false)
    private String fromPhone;

    @Column(nullable = false, length = 100)
    private String toPhone;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private String message;  
    
    public VoiceMessageModel(){
    }     

    public VoiceMessageModel(UUID voiceMessageId, VoiceChannelModel voiceChannel, MessageStatus messageStatus,
            String fromPhone, String toPhone, LocalDateTime creationDate, String message) {
        this.voiceMessageId = voiceMessageId;
        this.voiceChannel = voiceChannel;
        this.messageStatus = messageStatus;
        this.fromPhone = fromPhone;
        this.toPhone = toPhone;
        this.creationDate = creationDate;
        this.message = message;
    }

    public UUID getVoiceMessageId() {
        return voiceMessageId;
    }

    public void setVoiceMessageId(UUID voiceMessageId) {
        this.voiceMessageId = voiceMessageId;
    }

    public VoiceChannelModel getVoiceChannel() {
        return voiceChannel;
    }

    public void setVoiceChannel(VoiceChannelModel voiceChannel) {
        this.voiceChannel = voiceChannel;
    }

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getFromPhone() {
        return fromPhone;
    }

    public void setFromPhone(String fromPhone) {
        this.fromPhone = fromPhone;
    }

    public String getToPhone() {
        return toPhone;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((voiceMessageId == null) ? 0 : voiceMessageId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VoiceMessageModel other = (VoiceMessageModel) obj;
        if (voiceMessageId == null) {
            if (other.voiceMessageId != null)
                return false;
        } else if (!voiceMessageId.equals(other.voiceMessageId))
            return false;
        return true;
    }   
    
}
