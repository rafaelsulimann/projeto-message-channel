package com.weon.projetoweon.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.weon.projetoweon.models.channels.email.EmailMessageModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailMessageRepository extends JpaRepository<EmailMessageModel, UUID>, JpaSpecificationExecutor<EmailMessageModel> {
    
    @Query(value = "select * from tb_email_messages where email_channel_id = :emailMessageId", nativeQuery = true)
    List<EmailMessageModel> findAllEmailMessagesIntoEmailChannel(@Param("emailMessageId") UUID emailMessageId);    

    @Query(value = "select * from tb_email_messages where email_channel_id = :emailChannelId and email_message_id = :emailMessageId ", nativeQuery = true)
    Optional<EmailMessageModel> findEmailMessageIntoEmailChannel(@Param("emailChannelId") UUID emailChannelId, @Param("emailMessageId") UUID emailMessageId);
}
