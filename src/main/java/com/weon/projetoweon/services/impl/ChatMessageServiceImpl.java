package com.weon.projetoweon.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import com.weon.projetoweon.dtos.ChatMessageDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.chat.ChatChannelModel;
import com.weon.projetoweon.models.channels.chat.ChatMessageModel;
import com.weon.projetoweon.models.enums.MessageStatus;
import com.weon.projetoweon.repositories.ChatMessageRepository;
import com.weon.projetoweon.services.ChatMessageService;
import com.weon.projetoweon.services.exceptions.ChatMessageNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageServiceImpl implements ChatMessageService{

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public Page<ChatMessageModel> findAllChatMessagesIntoChatChannel(Specification<ChatMessageModel> spec,
    Pageable pageable){
        return chatMessageRepository.findAll(spec, pageable);
    }

    @Override
    public ChatMessageModel findChatMessageIntoChatChannel(UUID channelId, UUID messageId){
        Optional<ChatMessageModel> obj = chatMessageRepository.findChatMessageIntoChatChannel(channelId, messageId);
        return obj.orElseThrow(() -> new ChatMessageNotFoundException(messageId));
    }

    @Override
    public ChatMessageModel save(ChatMessageModel obj){
        return chatMessageRepository.save(obj);
    }

    @Override
    public void delete(UUID chatMessageId){
        chatMessageRepository.deleteById(chatMessageId);
    }

    @Override
    public ChatMessageModel insert(UserModel user, ChatChannelModel chatChannel, ChatMessageModel obj){
        obj.setChatChannel(chatChannel);
        obj.setMessageStatus(MessageStatus.ENVIADA);
        obj.setFromUser(user.getUserName());
        obj.setToUser(chatChannel.getToUser());
        obj.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        chatChannel.setLastChatMessage(obj.getMessage());
        chatChannel.setLastMessageDate(LocalDateTime.now(ZoneId.of("UTC")));
        chatChannel.setLastMessageSendFrom(user.getUserName());
        return obj;
    }

    @Override
    public ChatMessageModel fromDto(ChatMessageDto chatMessageDto) {
        ChatMessageModel obj = new ChatMessageModel();
        BeanUtils.copyProperties(chatMessageDto, obj);
        return obj;
    }
    
}
