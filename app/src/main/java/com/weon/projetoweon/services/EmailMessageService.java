package com.weon.projetoweon.services;

import java.util.UUID;

import com.weon.projetoweon.dtos.EmailMessageDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.email.EmailChannelModel;
import com.weon.projetoweon.models.channels.email.EmailMessageModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface EmailMessageService {

    public Page<EmailMessageModel> findAllEmailMessagesIntoEmailChannel(Specification<EmailMessageModel> spec,
    Pageable pageable);
    public EmailMessageModel findEmailMessageIntoEmailChannel(UUID channelId, UUID messageId);
    public EmailMessageModel save(EmailMessageModel obj);
    public void delete(UUID emailMessageId);
    public EmailMessageModel insert(UserModel user, EmailChannelModel emailChannel, EmailMessageModel obj);
    public EmailMessageModel fromDto(EmailMessageDto emailMessageDto);
    
}
