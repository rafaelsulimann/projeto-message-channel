package com.weon.projetoweon.services;

import java.util.UUID;

import com.weon.projetoweon.dtos.VoiceChannelDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.voice.VoiceChannelModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface VoiceChannelService {

    public Page<VoiceChannelModel> findAllVoicesChannelsIntoUser (Specification<VoiceChannelModel> spec, Pageable pageable);
    public VoiceChannelModel findVoiceChannelIntoUser (UUID userId, UUID voiceChannelId);
    public VoiceChannelModel existsVoiceChannelbyToPhone(UUID userId, String toPhone);
    public VoiceChannelModel save(VoiceChannelModel obj);
    public VoiceChannelModel insert(UserModel userModel, VoiceChannelModel voiceChannel);
    public void delete(VoiceChannelModel obj);
    public VoiceChannelModel fromDto(VoiceChannelDto voiceChannelDto);
    
}
