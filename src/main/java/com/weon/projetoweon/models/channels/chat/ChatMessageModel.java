package com.weon.projetoweon.models.channels.chat;

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
@Table(name = "tb_chat_messages")
public class ChatMessageModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID chatMessageId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chat_channel_id")
    private ChatChannelModel chatChannel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageStatus messageStatus;

    @Column(nullable = false)
    private String fromUser;

    @Column(nullable = false, length = 100)
    private String toUser;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private String message;

    public ChatMessageModel(){
    }    

    public ChatMessageModel(UUID chatMessageId, ChatChannelModel chatChannel, MessageStatus messageStatus, String fromUser,
            String toUser, LocalDateTime creationDate, String message) {
        this.chatMessageId = chatMessageId;
        this.chatChannel = chatChannel;
        this.messageStatus = messageStatus;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.creationDate = creationDate;
        this.message = message;
    }    

    public UUID getChatMessageId() {
        return chatMessageId;
    }

    public void setChatMessageId(UUID chatMessageId) {
        this.chatMessageId = chatMessageId;
    }

    public ChatChannelModel getChatChannel() {
        return chatChannel;
    }

    public void setChatChannel(ChatChannelModel chatChannel) {
        this.chatChannel = chatChannel;
    }

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
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
        result = prime * result + ((chatMessageId == null) ? 0 : chatMessageId.hashCode());
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
        ChatMessageModel other = (ChatMessageModel) obj;
        if (chatMessageId == null) {
            if (other.chatMessageId != null)
                return false;
        } else if (!chatMessageId.equals(other.chatMessageId))
            return false;
        return true;
    }    
    
}
