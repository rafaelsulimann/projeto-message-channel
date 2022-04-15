package com.weon.projetoweon.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.weon.projetoweon.models.channels.email.EmailChannelModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailChannelRepository extends JpaRepository<EmailChannelModel, UUID>, JpaSpecificationExecutor<EmailChannelModel>{
    
    @Query(value = "select * from tb_emails_channels where email_user_id = :userId", nativeQuery = true)
    List<EmailChannelModel> findAllEmailsChannelsIntoUser(@Param("userId") UUID userId);

    @Query(value = "select * from tb_emails_channels where email_user_id = :userId and email_channel_id = :emailChannelId ", nativeQuery = true)
    Optional<EmailChannelModel> findEmailChannelIntoUser(@Param("userId") UUID userId, @Param("emailChannelId") UUID emailChannelId);

    @Query(value = "select * from tb_emails_channels where email_user_id = :userId and to_email = :toEmail ", nativeQuery = true)
    EmailChannelModel existsEmailChannelbyToEmail(@Param("userId") UUID userId, @Param("toEmail") String toEmail);
}
