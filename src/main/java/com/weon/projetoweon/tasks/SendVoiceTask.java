package com.weon.projetoweon.tasks;

import java.time.LocalDateTime;
import java.time.ZoneId;

import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.voice.VoiceChannelModel;
import com.weon.projetoweon.models.channels.voice.VoiceMessageModel;
import com.weon.projetoweon.models.enums.MessageStatus;
import com.weon.projetoweon.services.VoiceChannelService;
import com.weon.projetoweon.services.VoiceMessageService;
import com.weon.projetoweon.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendVoiceTask {
    
    @Autowired
    private UserService userService;

    @Autowired
    private VoiceChannelService voiceChannelService;

    @Autowired
    private VoiceMessageService voiceMessageService;

    @Async
    public void sendMessage(VoiceMessageModel voiceMessageModel){
        System.out.println(voiceMessageModel.getFromPhone() + " enviou uma mensagem para " + voiceMessageModel.getToPhone());
        UserModel toUser = userService.findByPhone(voiceMessageModel.getToPhone());
        if(voiceChannelService.existsVoiceChannelbyToPhone(toUser.getUserId(), voiceMessageModel.getFromPhone()) == null){
            voiceChannelService.save(new VoiceChannelModel(null, voiceMessageModel.getFromPhone(), LocalDateTime.now(ZoneId.of("UTC")), voiceMessageModel.getCreationDate(), voiceMessageModel.getFromPhone(), voiceMessageModel.getMessage(), toUser));
            VoiceChannelModel voiceChannel = voiceChannelService.existsVoiceChannelbyToPhone(toUser.getUserId(), voiceMessageModel.getFromPhone());
            voiceMessageService.save(new VoiceMessageModel(null, voiceChannel, MessageStatus.RECEBIDA, voiceMessageModel.getFromPhone(), voiceMessageModel.getToPhone(), voiceMessageModel.getCreationDate(), voiceMessageModel.getMessage()));
        }
        else{
            VoiceChannelModel voiceChannel = voiceChannelService.existsVoiceChannelbyToPhone(toUser.getUserId(), voiceMessageModel.getFromPhone());
            voiceMessageService.save(new VoiceMessageModel(null, voiceChannel, MessageStatus.RECEBIDA, voiceMessageModel.getFromPhone(), voiceMessageModel.getToPhone(), voiceMessageModel.getCreationDate(), voiceMessageModel.getMessage()));        
        }
        System.out.println(voiceMessageModel.getToPhone() + " recebeu a mensagem de " + voiceMessageModel.getFromPhone());
    }
}
