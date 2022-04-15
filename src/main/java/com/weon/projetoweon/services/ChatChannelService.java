package com.weon.projetoweon.services;

import java.util.UUID;

import com.weon.projetoweon.dtos.ChatChannelDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.chat.ChatChannelModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface ChatChannelService {
    
    public Page<ChatChannelModel> findAllChatsChannelsIntoUser(Specification<ChatChannelModel> spec, Pageable pageable);
    public ChatChannelModel findChatChannelIntoUser (UUID userId, UUID chatChannelId);
    public ChatChannelModel existsChatChannelbyToUser(UUID userId, String toUser);
    public ChatChannelModel save(ChatChannelModel obj);
    public ChatChannelModel insert(UserModel userModel, ChatChannelModel chatChannel);
    public void delete(ChatChannelModel obj);
    public ChatChannelModel fromDto(ChatChannelDto chatChannelDto);
}
