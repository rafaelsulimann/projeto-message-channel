package com.weon.projetoweon.models.channels.voice;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.weon.projetoweon.models.UserModel;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "tb_voices_channels")
public class VoiceChannelModel extends RepresentationModel<VoiceChannelModel> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID voiceChannelId;

    @Column(nullable = false, length = 20)
    private String toPhone;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime lastMessageDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column
    private String lastMessageSendFrom;

    @Column
    private String lastVoiceMessage;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "voice_user_id")
    private UserModel userVoiceChannel;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "voiceChannel", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<VoiceMessageModel> voiceMessages = new HashSet<>();

    public VoiceChannelModel() {
    }     

    public VoiceChannelModel(UUID voiceChannelId, String toPhone, LocalDateTime creationDate,
            LocalDateTime lastMessageDate, String lastMessageSendFrom, String lastVoiceMessage,
            UserModel userVoiceChannel) {
        this.voiceChannelId = voiceChannelId;
        this.toPhone = toPhone;
        this.creationDate = creationDate;
        this.lastMessageDate = lastMessageDate;
        this.lastMessageSendFrom = lastMessageSendFrom;
        this.lastVoiceMessage = lastVoiceMessage;
        this.userVoiceChannel = userVoiceChannel;
    }

    public UUID getVoiceChannelId() {
        return voiceChannelId;
    }

    public void setVoiceChannelId(UUID voiceChannelId) {
        this.voiceChannelId = voiceChannelId;
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

    public LocalDateTime getLastMessageDate() {
        return lastMessageDate;
    }

    public void setLastMessageDate(LocalDateTime lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    public String getLastMessageSendFrom() {
        return lastMessageSendFrom;
    }

    public void setLastMessageSendFrom(String lastMessageSendFrom) {
        this.lastMessageSendFrom = lastMessageSendFrom;
    }

    public String getLastVoiceMessage() {
        return lastVoiceMessage;
    }

    public void setLastVoiceMessage(String lastVoiceMessage) {
        this.lastVoiceMessage = lastVoiceMessage;
    }

    public UserModel getUserVoiceChannel() {
        return userVoiceChannel;
    }

    public void setUserVoiceChannel(UserModel userVoiceChannel) {
        this.userVoiceChannel = userVoiceChannel;
    }

    public Set<VoiceMessageModel> getVoiceMessages() {
        return voiceMessages;
    }

    public void setVoiceMessages(Set<VoiceMessageModel> voiceMessages) {
        this.voiceMessages = voiceMessages;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((voiceChannelId == null) ? 0 : voiceChannelId.hashCode());
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
        VoiceChannelModel other = (VoiceChannelModel) obj;
        if (voiceChannelId == null) {
            if (other.voiceChannelId != null)
                return false;
        } else if (!voiceChannelId.equals(other.voiceChannelId))
            return false;
        return true;
    }    
    
}
