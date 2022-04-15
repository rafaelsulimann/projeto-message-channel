package com.weon.projetoweon.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import com.weon.projetoweon.dtos.VoiceMessageDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.voice.VoiceChannelModel;
import com.weon.projetoweon.models.channels.voice.VoiceMessageModel;
import com.weon.projetoweon.models.enums.MessageStatus;
import com.weon.projetoweon.repositories.VoiceMessageRepository;
import com.weon.projetoweon.services.VoiceMessageService;
import com.weon.projetoweon.services.exceptions.VoiceMessageNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class VoiceMessageServiceImpl implements VoiceMessageService {

    @Autowired
    private VoiceMessageRepository voiceMessageRepository;

    @Override
    public Page<VoiceMessageModel> findAllVoiceMessagesIntoVoiceChannel(Specification<VoiceMessageModel> spec,
            Pageable pageable) {
        return voiceMessageRepository.findAll(spec, pageable);
    }

    @Override
    public VoiceMessageModel findVoiceMessageIntoVoiceChannel(UUID channelId, UUID messageId) {
        Optional<VoiceMessageModel> obj = voiceMessageRepository.findVoiceMessageIntoVoiceChannel(channelId, messageId);
        return obj.orElseThrow(() -> new VoiceMessageNotFoundException(messageId));
    }

    @Override
    public VoiceMessageModel save(VoiceMessageModel obj) {
        return voiceMessageRepository.save(obj);
    }

    @Override
    public void delete(UUID voiceMessageId) {
        voiceMessageRepository.deleteById(voiceMessageId);
    }

    @Override
    public VoiceMessageModel insert(UserModel user, VoiceChannelModel voiceChannel, VoiceMessageModel obj) {
        obj.setVoiceChannel(voiceChannel);
        obj.setMessageStatus(MessageStatus.ENVIADA);
        obj.setFromPhone(user.getPhone());
        obj.setToPhone(voiceChannel.getToPhone());
        obj.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        voiceChannel.setLastVoiceMessage(obj.getMessage());
        voiceChannel.setLastMessageDate(LocalDateTime.now(ZoneId.of("UTC")));
        voiceChannel.setLastMessageSendFrom(user.getPhone());
        return obj;
    }

    @Override
    public VoiceMessageModel fromDto(VoiceMessageDto voiceMessageDto) {
        VoiceMessageModel obj = new VoiceMessageModel();
        BeanUtils.copyProperties(voiceMessageDto, obj);
        return obj;
    }

}
