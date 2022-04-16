package com.weon.projetoweon.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.weon.projetoweon.models.channels.voice.VoiceMessageModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoiceMessageRepository extends JpaRepository<VoiceMessageModel, UUID>, JpaSpecificationExecutor<VoiceMessageModel>{
    
    @Query(value = "select * from tb_voice_messages where voice_channel_id = :voiceMessageId", nativeQuery = true)
    List<VoiceMessageModel> findAllVoiceMessagesIntoVoiceChannel(@Param("voiceMessageId") UUID voiceMessageId); 
    
    @Query(value = "select * from tb_voice_messages where voice_channel_id = :voiceChannelId and voice_message_id = :voiceMessageId ", nativeQuery = true)
    Optional<VoiceMessageModel> findVoiceMessageIntoVoiceChannel(@Param("voiceChannelId") UUID voiceChannelId, @Param("voiceMessageId") UUID voiceMessageId);
}
