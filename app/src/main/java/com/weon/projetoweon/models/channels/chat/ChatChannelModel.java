package com.weon.projetoweon.models.channels.chat;

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
@Table(name = "tb_chats_channels")
public class ChatChannelModel extends RepresentationModel<ChatChannelModel> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID chatChannelId;

    @Column(nullable = false, length = 100)
    private String toUser;

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
    private String lastChatMessage;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chat_user_id")
    private UserModel userChatChannel;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "chatChannel", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<ChatMessageModel> chatMessages = new HashSet<>();

    public ChatChannelModel(){
    }      

    public ChatChannelModel(UUID chatChannelId, String toUser, LocalDateTime creationDate,
            LocalDateTime lastMessageDate, String lastMessageSendFrom, String lastChatMessage,
            UserModel userChatChannel) {
        this.chatChannelId = chatChannelId;
        this.toUser = toUser;
        this.creationDate = creationDate;
        this.lastMessageDate = lastMessageDate;
        this.lastMessageSendFrom = lastMessageSendFrom;
        this.lastChatMessage = lastChatMessage;
        this.userChatChannel = userChatChannel;
    }    

    public UUID getChatChannelId() {
        return chatChannelId;
    }

    public void setChatChannelId(UUID chatChannelId) {
        this.chatChannelId = chatChannelId;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
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

    public String getLastChatMessage() {
        return lastChatMessage;
    }

    public void setLastChatMessage(String lastChatMessage) {
        this.lastChatMessage = lastChatMessage;
    }

    public UserModel getUserChatChannel() {
        return userChatChannel;
    }

    public void setUserChatChannel(UserModel userChatChannel) {
        this.userChatChannel = userChatChannel;
    }

    public Set<ChatMessageModel> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(Set<ChatMessageModel> chatMessages) {
        this.chatMessages = chatMessages;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((chatChannelId == null) ? 0 : chatChannelId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChatChannelModel other = (ChatChannelModel) obj;
        if (chatChannelId == null) {
            if (other.chatChannelId != null)
                return false;
        } else if (!chatChannelId.equals(other.chatChannelId))
            return false;
        return true;
    }

         
    
}
