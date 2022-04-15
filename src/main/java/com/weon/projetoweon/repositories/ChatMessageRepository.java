package com.weon.projetoweon.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.weon.projetoweon.models.channels.chat.ChatMessageModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageModel, UUID>, JpaSpecificationExecutor<ChatMessageModel> {
    
    @Query(value = "select * from tb_chat_messages where chat_channel_id = :chatChannelId", nativeQuery = true)
    List<ChatMessageModel> findAllChatMessagesIntoChatChannel(@Param("chatChannelId") UUID chatChannelId);

    @Query(value = "select * from tb_chat_messages where chat_channel_id = :chatChannelId and chat_message_id = :chatMessageId ", nativeQuery = true)
    Optional<ChatMessageModel> findChatMessageIntoChatChannel(@Param("chatChannelId") UUID chatChannelId, @Param("chatMessageId") UUID chatMessageId);
}
