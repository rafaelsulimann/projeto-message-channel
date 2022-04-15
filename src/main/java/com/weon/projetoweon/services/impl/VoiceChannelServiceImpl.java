package com.weon.projetoweon.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.weon.projetoweon.dtos.VoiceChannelDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.voice.VoiceChannelModel;
import com.weon.projetoweon.models.channels.voice.VoiceMessageModel;
import com.weon.projetoweon.repositories.VoiceChannelRepository;
import com.weon.projetoweon.repositories.VoiceMessageRepository;
import com.weon.projetoweon.services.VoiceChannelService;
import com.weon.projetoweon.services.exceptions.VoiceChannelNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class VoiceChannelServiceImpl implements VoiceChannelService {

    @Autowired
    private VoiceChannelRepository voiceChannelRepository;

    @Autowired
    private VoiceMessageRepository voiceMessageRepository;

    @Override
    public Page<VoiceChannelModel> findAllVoicesChannelsIntoUser(Specification<VoiceChannelModel> spec,
            Pageable pageable) {
        return voiceChannelRepository.findAll(spec, pageable);
    }

    @Override
    public VoiceChannelModel findVoiceChannelIntoUser(UUID userId, UUID voiceChannelId) {
        Optional<VoiceChannelModel> obj = voiceChannelRepository.findVoiceChannelIntoUser(userId, voiceChannelId);
        return obj.orElseThrow(() -> new VoiceChannelNotFoundException(voiceChannelId));
    }

    @Override
    public VoiceChannelModel existsVoiceChannelbyToPhone(UUID userId, String toPhone){
        VoiceChannelModel obj = voiceChannelRepository.existsVoiceChannelbyToPhone(userId, toPhone);
        return obj;
    }

    @Override
    public VoiceChannelModel save(VoiceChannelModel obj) {
        return voiceChannelRepository.save(obj);
    }

    @Override
    public VoiceChannelModel insert(UserModel userModel, VoiceChannelModel voiceChannel) {
        voiceChannel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        voiceChannel.setUserVoiceChannel(userModel);
        return voiceChannel;
    }

    @Override
    public void delete(VoiceChannelModel obj) {
        List<VoiceMessageModel> voiceMessageList = voiceMessageRepository
                .findAllVoiceMessagesIntoVoiceChannel(obj.getVoiceChannelId());
        if (!voiceMessageList.isEmpty()) {
            voiceMessageRepository.deleteAll(voiceMessageList);
        }
        voiceChannelRepository.delete(obj);
    }

    @Override
    public VoiceChannelModel fromDto(VoiceChannelDto voiceChannelDto) {
        VoiceChannelModel obj = new VoiceChannelModel();
        BeanUtils.copyProperties(voiceChannelDto, obj);
        return obj;
    }

}
