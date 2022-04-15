package com.weon.projetoweon.services;

import java.util.UUID;

import com.weon.projetoweon.dtos.ChatMessageDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.chat.ChatChannelModel;
import com.weon.projetoweon.models.channels.chat.ChatMessageModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface ChatMessageService {
    
    public Page<ChatMessageModel> findAllChatMessagesIntoChatChannel(Specification<ChatMessageModel> spec,
    Pageable pageable);
    public ChatMessageModel findChatMessageIntoChatChannel(UUID channelId, UUID messageId);
    public ChatMessageModel save(ChatMessageModel obj);
    public void delete(UUID chatMessageId);
    public ChatMessageModel insert(UserModel user, ChatChannelModel chatChannel, ChatMessageModel obj);
    public ChatMessageModel fromDto(ChatMessageDto chatMessageDto);
}
