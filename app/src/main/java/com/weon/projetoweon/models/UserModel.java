package com.weon.projetoweon.models;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.weon.projetoweon.models.channels.chat.ChatChannelModel;
import com.weon.projetoweon.models.channels.email.EmailChannelModel;
import com.weon.projetoweon.models.channels.voice.VoiceChannelModel;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "tb_users")
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    @Column(nullable = false, length = 50)
    private String userName;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime lastUpdateDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "userChatChannel", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<ChatChannelModel> chatChannels = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "userEmailChannel", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<EmailChannelModel> emailChannels = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "userVoiceChannel", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<VoiceChannelModel> voiceChannels = new HashSet<>();

    public UserModel() {
    }    

    public UserModel(UUID userId, String userName, String fullName, String email, String phone,
            LocalDateTime creationDate, LocalDateTime lastUpdateDate) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Set<ChatChannelModel> getChatChannels() {
        return chatChannels;
    }

    public void setChatChannels(Set<ChatChannelModel> chatChannels) {
        this.chatChannels = chatChannels;
    }

    public Set<EmailChannelModel> getEmailChannels() {
        return emailChannels;
    }

    public void setEmailChannels(Set<EmailChannelModel> emailChannels) {
        this.emailChannels = emailChannels;
    }

    public Set<VoiceChannelModel> getVoiceChannels() {
        return voiceChannels;
    }

    public void setVoiceChannels(Set<VoiceChannelModel> voiceChannels) {
        this.voiceChannels = voiceChannels;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
        UserModel other = (UserModel) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
