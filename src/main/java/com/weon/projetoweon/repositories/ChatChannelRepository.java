package com.weon.projetoweon.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.weon.projetoweon.models.channels.chat.ChatChannelModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatChannelRepository extends JpaRepository<ChatChannelModel, UUID>, JpaSpecificationExecutor<ChatChannelModel> {
    
    @Query(value = "select * from tb_chats_channels where chat_user_id = :userId", nativeQuery = true)
    List<ChatChannelModel> findAllChatsChannelsIntoUser(@Param("userId") UUID userId);

    @Query(value = "select * from tb_chats_channels where chat_user_id = :userId and chat_channel_id = :chatChannelId ", nativeQuery = true)
    Optional<ChatChannelModel> findChatChannelIntoUser(@Param("userId") UUID userId, @Param("chatChannelId") UUID chatChannelId);

    @Query(value = "select * from tb_chats_channels where chat_user_id = :userId and to_user = :toUser ", nativeQuery = true)
    ChatChannelModel existsChatChannelbyToUser(@Param("userId") UUID userId, @Param("toUser") String toUser);
}
