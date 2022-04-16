package com.weon.projetoweon.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import com.weon.projetoweon.dtos.EmailMessageDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.email.EmailChannelModel;
import com.weon.projetoweon.models.channels.email.EmailMessageModel;
import com.weon.projetoweon.models.enums.MessageStatus;
import com.weon.projetoweon.repositories.EmailMessageRepository;
import com.weon.projetoweon.services.EmailMessageService;
import com.weon.projetoweon.services.exceptions.EmailMessageNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class EmailMessageServiceImpl implements EmailMessageService {

    @Autowired
    private EmailMessageRepository emailMessageRepository;

    @Override
    public Page<EmailMessageModel> findAllEmailMessagesIntoEmailChannel(Specification<EmailMessageModel> spec,
            Pageable pageable) {
        return emailMessageRepository.findAll(spec, pageable);
    }

    @Override
    public EmailMessageModel findEmailMessageIntoEmailChannel(UUID channelId, UUID messageId) {
        Optional<EmailMessageModel> obj = emailMessageRepository.findEmailMessageIntoEmailChannel(channelId, messageId);
        return obj.orElseThrow(() -> new EmailMessageNotFoundException(messageId));
    }

    @Override
    public EmailMessageModel save(EmailMessageModel obj) {
        return emailMessageRepository.save(obj);
    }

    @Override
    public void delete(UUID emailMessageId) {
        emailMessageRepository.deleteById(emailMessageId);
    }

    @Override
    public EmailMessageModel insert(UserModel user, EmailChannelModel emailChannel, EmailMessageModel obj) {
        obj.setEmailChannel(emailChannel);
        obj.setMessageStatus(MessageStatus.ENVIADA);
        obj.setFromEmail(user.getEmail());
        obj.setToEmail(emailChannel.getToEmail());
        obj.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        emailChannel.setLastEmailMessage(obj.getMessage());
        emailChannel.setLastEmailDate(LocalDateTime.now(ZoneId.of("UTC")));
        emailChannel.setLastEmailSendFrom(user.getEmail());
        return obj;
    }

    @Override
    public EmailMessageModel fromDto(EmailMessageDto emailMessageDto) {
        EmailMessageModel obj = new EmailMessageModel();
        BeanUtils.copyProperties(emailMessageDto, obj);
        return obj;
    }

}
