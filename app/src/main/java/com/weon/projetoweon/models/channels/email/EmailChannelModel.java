package com.weon.projetoweon.models.channels.email;

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
@Table(name = "tb_emails_channels")
public class EmailChannelModel extends RepresentationModel<EmailChannelModel> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID emailChannelId;

    @Column(nullable = false, length = 100)
    private String toEmail;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime lastEmailDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column
    private String lastEmailSendFrom;

    @Column
    private String lastEmailMessage;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "email_user_id")
    private UserModel userEmailChannel;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "emailChannel", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<EmailMessageModel> emailMessages = new HashSet<>();

    public EmailChannelModel(){
    }       

    public EmailChannelModel(UUID emailChannelId, String toEmail, LocalDateTime creationDate,
            LocalDateTime lastEmailDate, String lastEmailSendFrom, String lastEmailMessage,
            UserModel userEmailChannel) {
        this.emailChannelId = emailChannelId;
        this.toEmail = toEmail;
        this.creationDate = creationDate;
        this.lastEmailDate = lastEmailDate;
        this.lastEmailSendFrom = lastEmailSendFrom;
        this.lastEmailMessage = lastEmailMessage;
        this.userEmailChannel = userEmailChannel;
    }

    public UUID getEmailChannelId() {
        return emailChannelId;
    }

    public void setEmailChannelId(UUID emailChannelId) {
        this.emailChannelId = emailChannelId;
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

    public LocalDateTime getLastEmailDate() {
        return lastEmailDate;
    }

    public void setLastEmailDate(LocalDateTime lastEmailDate) {
        this.lastEmailDate = lastEmailDate;
    }

    public String getLastEmailSendFrom() {
        return lastEmailSendFrom;
    }

    public void setLastEmailSendFrom(String lastEmailSendFrom) {
        this.lastEmailSendFrom = lastEmailSendFrom;
    }

    public String getLastEmailMessage() {
        return lastEmailMessage;
    }

    public void setLastEmailMessage(String lastEmailMessage) {
        this.lastEmailMessage = lastEmailMessage;
    }

    public UserModel getUserEmailChannel() {
        return userEmailChannel;
    }

    public void setUserEmailChannel(UserModel userEmailChannel) {
        this.userEmailChannel = userEmailChannel;
    }

    public Set<EmailMessageModel> getEmailMessages() {
        return emailMessages;
    }

    public void setEmailMessages(Set<EmailMessageModel> emailMessages) {
        this.emailMessages = emailMessages;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((emailChannelId == null) ? 0 : emailChannelId.hashCode());
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
        EmailChannelModel other = (EmailChannelModel) obj;
        if (emailChannelId == null) {
            if (other.emailChannelId != null)
                return false;
        } else if (!emailChannelId.equals(other.emailChannelId))
            return false;
        return true;
    }    
    
}
