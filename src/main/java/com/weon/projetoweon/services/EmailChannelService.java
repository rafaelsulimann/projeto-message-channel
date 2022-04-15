package com.weon.projetoweon.services;

import java.util.UUID;

import com.weon.projetoweon.dtos.EmailChannelDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.email.EmailChannelModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface EmailChannelService {

    public Page<EmailChannelModel> findAllEmailsChannelsIntoUser(Specification<EmailChannelModel> spec, Pageable pageable);
    public EmailChannelModel findEmailChannelIntoUser (UUID userId, UUID emailChannelId);
    public EmailChannelModel existsEmailChannelbyToEmail(UUID userId, String toEmail);
    public EmailChannelModel save(EmailChannelModel obj);
    public EmailChannelModel insert(UserModel userModel, EmailChannelModel emailChannel);
    public void delete(EmailChannelModel obj);
    public EmailChannelModel fromDto(EmailChannelDto emailChannelDto);
    
}
