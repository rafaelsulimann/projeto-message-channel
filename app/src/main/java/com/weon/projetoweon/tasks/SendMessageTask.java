package com.weon.projetoweon.tasks;

import java.time.LocalDateTime;
import java.time.ZoneId;

import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.chat.ChatChannelModel;
import com.weon.projetoweon.models.channels.chat.ChatMessageModel;
import com.weon.projetoweon.models.enums.MessageStatus;
import com.weon.projetoweon.services.ChatChannelService;
import com.weon.projetoweon.services.ChatMessageService;
import com.weon.projetoweon.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendMessageTask {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatChannelService chatChannelService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Async
    public void sendMessage(ChatMessageModel chatMessageModel){
        System.out.println(chatMessageModel.getFromUser() + " enviou uma mensagem para " + chatMessageModel.getToUser());
        UserModel toUser = userService.findByUserName(chatMessageModel.getToUser());
        if(chatChannelService.existsChatChannelbyToUser(toUser.getUserId(), chatMessageModel.getFromUser()) == null){
            chatChannelService.save(new ChatChannelModel(null, chatMessageModel.getFromUser(), LocalDateTime.now(ZoneId.of("UTC")), chatMessageModel.getCreationDate(), chatMessageModel.getFromUser(), chatMessageModel.getMessage(), toUser));
            ChatChannelModel chatChannel = chatChannelService.existsChatChannelbyToUser(toUser.getUserId(), chatMessageModel.getFromUser());
            chatMessageService.save(new ChatMessageModel(null, chatChannel, MessageStatus.RECEBIDA, chatMessageModel.getFromUser(), chatMessageModel.getToUser(), chatMessageModel.getCreationDate(), chatMessageModel.getMessage()));
        }
        else{
            ChatChannelModel chatChannel = chatChannelService.existsChatChannelbyToUser(toUser.getUserId(), chatMessageModel.getFromUser());
            chatMessageService.save(new ChatMessageModel(null, chatChannel, MessageStatus.RECEBIDA, chatMessageModel.getFromUser(), chatMessageModel.getToUser(), chatMessageModel.getCreationDate(), chatMessageModel.getMessage()));        
        }
        System.out.println(chatMessageModel.getToUser() + " recebeu a mensagem de " + chatMessageModel.getFromUser());
    }
    
}
