package com.weon.projetoweon.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.weon.projetoweon.models.channels.voice.VoiceChannelModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoiceChannelRepository extends JpaRepository<VoiceChannelModel, UUID>, JpaSpecificationExecutor<VoiceChannelModel>{
    
    @Query(value = "select * from tb_voices_channels where voice_user_id = :userId", nativeQuery = true)
    List<VoiceChannelModel> findAllVoicesChannelsIntoUser(@Param("userId") UUID userId);

    @Query(value = "select * from tb_voices_channels where voice_user_id = :userId and voice_channel_id = :voiceChannelId ", nativeQuery = true)
    Optional<VoiceChannelModel> findVoiceChannelIntoUser(@Param("userId") UUID userId, @Param("voiceChannelId") UUID voiceChannelId);

    @Query(value = "select * from tb_voices_channels where voice_user_id = :userId and to_phone = :toPhone ", nativeQuery = true)
    VoiceChannelModel existsVoiceChannelbyToPhone(@Param("userId") UUID userId, @Param("toPhone") String toPhone);
}
