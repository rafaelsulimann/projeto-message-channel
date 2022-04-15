package com.weon.projetoweon.models.channels.email;

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
@Table(name = "tb_email_messages")
public class EmailMessageModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID emailMessageId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "email_channel_id")
    private EmailChannelModel emailChannel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageStatus messageStatus;

    @Column(nullable = false)
    private String fromEmail;

    @Column(nullable = false, length = 100)
    private String toEmail;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private String message;   
    
    public EmailMessageModel (){
    }          

    public EmailMessageModel(UUID emailMessageId, EmailChannelModel emailChannel, MessageStatus messageStatus,
            String fromEmail, String toEmail, LocalDateTime creationDate, String message) {
        this.emailMessageId = emailMessageId;
        this.emailChannel = emailChannel;
        this.messageStatus = messageStatus;
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
        this.creationDate = creationDate;
        this.message = message;
    }

    public UUID getEmailMessageId() {
        return emailMessageId;
    }

    public void setEmailMessageId(UUID emailMessageId) {
        this.emailMessageId = emailMessageId;
    }

    public EmailChannelModel getEmailChannel() {
        return emailChannel;
    }

    public void setEmailChannel(EmailChannelModel emailChannel) {
        this.emailChannel = emailChannel;
    }

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
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
        result = prime * result + ((emailMessageId == null) ? 0 : emailMessageId.hashCode());
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
        EmailMessageModel other = (EmailMessageModel) obj;
        if (emailMessageId == null) {
            if (other.emailMessageId != null)
                return false;
        } else if (!emailMessageId.equals(other.emailMessageId))
            return false;
        return true;
    }    
    
}
