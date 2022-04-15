package com.weon.projetoweon.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.weon.projetoweon.dtos.EmailChannelDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.email.EmailChannelModel;
import com.weon.projetoweon.models.channels.email.EmailMessageModel;
import com.weon.projetoweon.repositories.EmailChannelRepository;
import com.weon.projetoweon.repositories.EmailMessageRepository;
import com.weon.projetoweon.services.EmailChannelService;
import com.weon.projetoweon.services.exceptions.EmailChannelNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class EmailChannelServiceImpl implements EmailChannelService {

    @Autowired
    private EmailChannelRepository emailChannelRepository;

    @Autowired
    private EmailMessageRepository emailMessageRepository;

    @Override
    public Page<EmailChannelModel> findAllEmailsChannelsIntoUser(Specification<EmailChannelModel> spec,
            Pageable pageable) {
        return emailChannelRepository.findAll(spec, pageable);
    }
    
    @Override
    public EmailChannelModel findEmailChannelIntoUser(UUID userId, UUID emailChannelId) {
        Optional<EmailChannelModel> obj = emailChannelRepository.findEmailChannelIntoUser(userId, emailChannelId);
        return obj.orElseThrow(() -> new EmailChannelNotFoundException(emailChannelId));
    }

    @Override
    public EmailChannelModel existsEmailChannelbyToEmail(UUID userId, String toEmail){
        EmailChannelModel obj = emailChannelRepository.existsEmailChannelbyToEmail(userId, toEmail);
        return obj;
    }

    @Override
    public EmailChannelModel save(EmailChannelModel obj) {
        return emailChannelRepository.save(obj);
    }

    @Override
    public EmailChannelModel insert(UserModel userModel, EmailChannelModel emailChannel) {
        emailChannel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        emailChannel.setUserEmailChannel(userModel);
        return emailChannel;
    }

    @Override
    public void delete(EmailChannelModel obj) {
        List<EmailMessageModel> emailMessageList = emailMessageRepository
                .findAllEmailMessagesIntoEmailChannel(obj.getEmailChannelId());
        if (!emailMessageList.isEmpty()) {
            emailMessageRepository.deleteAll(emailMessageList);
        }
        emailChannelRepository.delete(obj);
    }

    @Override
    public EmailChannelModel fromDto(EmailChannelDto emailChannelDto) {
        EmailChannelModel obj = new EmailChannelModel();
        BeanUtils.copyProperties(emailChannelDto, obj);
        return obj;
    }

}
