package com.weon.projetoweon.services;

import java.util.UUID;

import com.weon.projetoweon.dtos.VoiceMessageDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.voice.VoiceChannelModel;
import com.weon.projetoweon.models.channels.voice.VoiceMessageModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface VoiceMessageService {

    public Page<VoiceMessageModel> findAllVoiceMessagesIntoVoiceChannel(Specification<VoiceMessageModel> spec,
    Pageable pageable);
    public VoiceMessageModel findVoiceMessageIntoVoiceChannel(UUID channelId, UUID messageId);
    public VoiceMessageModel save(VoiceMessageModel obj);
    public void delete(UUID voiceMessageId);
    public VoiceMessageModel insert(UserModel user, VoiceChannelModel voiceChannel, VoiceMessageModel obj);
    public VoiceMessageModel fromDto(VoiceMessageDto voiceMessageDto);
    
}
