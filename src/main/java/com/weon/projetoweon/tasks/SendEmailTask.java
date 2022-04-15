package com.weon.projetoweon.tasks;

import java.time.LocalDateTime;
import java.time.ZoneId;

import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.email.EmailChannelModel;
import com.weon.projetoweon.models.channels.email.EmailMessageModel;
import com.weon.projetoweon.models.enums.MessageStatus;
import com.weon.projetoweon.services.EmailChannelService;
import com.weon.projetoweon.services.EmailMessageService;
import com.weon.projetoweon.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendEmailTask {
    
    @Autowired
    private UserService userService;

    @Autowired
    private EmailChannelService emailChannelService;

    @Autowired
    private EmailMessageService emailMessageService;

    @Async
    public void sendMessage(EmailMessageModel emailMessageModel){
        System.out.println(emailMessageModel.getFromEmail() + " enviou um email para " + emailMessageModel.getToEmail());
        UserModel toUser = userService.findByEmail(emailMessageModel.getToEmail());
        if(emailChannelService.existsEmailChannelbyToEmail(toUser.getUserId(), emailMessageModel.getFromEmail()) == null){
            emailChannelService.save(new EmailChannelModel(null, emailMessageModel.getFromEmail(), LocalDateTime.now(ZoneId.of("UTC")), emailMessageModel.getCreationDate(), emailMessageModel.getFromEmail(), emailMessageModel.getMessage(), toUser));
            EmailChannelModel emailChannel = emailChannelService.existsEmailChannelbyToEmail(toUser.getUserId(), emailMessageModel.getFromEmail());
            emailMessageService.save(new EmailMessageModel(null, emailChannel, MessageStatus.RECEBIDA, emailMessageModel.getFromEmail(), emailMessageModel.getToEmail(), emailMessageModel.getCreationDate(), emailMessageModel.getMessage()));
        }
        else{
            EmailChannelModel emailChannel = emailChannelService.existsEmailChannelbyToEmail(toUser.getUserId(), emailMessageModel.getFromEmail());
            emailMessageService.save(new EmailMessageModel(null, emailChannel, MessageStatus.RECEBIDA, emailMessageModel.getFromEmail(), emailMessageModel.getToEmail(), emailMessageModel.getCreationDate(), emailMessageModel.getMessage()));        
        }
        System.out.println(emailMessageModel.getToEmail() + " recebeu o email de " + emailMessageModel.getFromEmail());
    }
}
