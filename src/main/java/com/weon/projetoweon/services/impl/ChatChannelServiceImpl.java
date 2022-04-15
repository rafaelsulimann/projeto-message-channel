package com.weon.projetoweon.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.weon.projetoweon.dtos.ChatChannelDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.chat.ChatChannelModel;
import com.weon.projetoweon.models.channels.chat.ChatMessageModel;
import com.weon.projetoweon.repositories.ChatChannelRepository;
import com.weon.projetoweon.repositories.ChatMessageRepository;
import com.weon.projetoweon.services.ChatChannelService;
import com.weon.projetoweon.services.exceptions.ChatChannelNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ChatChannelServiceImpl implements ChatChannelService{
    
    @Autowired
    private ChatChannelRepository chatChannelRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public Page<ChatChannelModel> findAllChatsChannelsIntoUser(Specification<ChatChannelModel> spec,
            Pageable pageable) {        
        return chatChannelRepository.findAll(spec, pageable);
    }

    @Override
    public ChatChannelModel findChatChannelIntoUser (UUID userId, UUID chatChannelId){
        Optional<ChatChannelModel> obj = chatChannelRepository.findChatChannelIntoUser(userId, chatChannelId);
        return obj.orElseThrow(() -> new ChatChannelNotFoundException(chatChannelId));
    }

    @Override
    public ChatChannelModel existsChatChannelbyToUser(UUID userId, String toUser){
        ChatChannelModel obj = chatChannelRepository.existsChatChannelbyToUser(userId, toUser);
        return obj;
    }

    @Override
    public ChatChannelModel save(ChatChannelModel obj){
        return chatChannelRepository.save(obj);
    }

    @Override
    public ChatChannelModel insert(UserModel userModel, ChatChannelModel chatChannel){
        chatChannel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        chatChannel.setUserChatChannel(userModel);
        return chatChannel;
    }

    @Override
    public void delete(ChatChannelModel obj){
        List<ChatMessageModel> chatMessageList = chatMessageRepository.findAllChatMessagesIntoChatChannel(obj.getChatChannelId());
        if(!chatMessageList.isEmpty()){
            chatMessageRepository.deleteAll(chatMessageList);
        }
        chatChannelRepository.delete(obj);
    }

    @Override
    public ChatChannelModel fromDto(ChatChannelDto chatChannelDto){
        ChatChannelModel obj = new ChatChannelModel();
        BeanUtils.copyProperties(chatChannelDto, obj);
        return obj;
    }
    
}
