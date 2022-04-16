package com.weon.projetoweon.controllers;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import com.weon.projetoweon.dtos.EmailMessageDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.email.EmailChannelModel;
import com.weon.projetoweon.models.channels.email.EmailMessageModel;
import com.weon.projetoweon.services.EmailChannelService;
import com.weon.projetoweon.services.EmailMessageService;
import com.weon.projetoweon.services.UserService;
import com.weon.projetoweon.specifications.SpecificationTemplate;
import com.weon.projetoweon.tasks.SendEmailTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@EnableAsync
public class EmailMessageController {

    @Autowired
    private EmailMessageService emailMessageService;

    @Autowired
    private EmailChannelService emailChannelService;

    @Autowired
    private UserService userService;

    @Autowired
    private SendEmailTask sendEmailTask;

    @GetMapping(value = "/{userName}/emailchannels/{emailChannelId}")
    public ResponseEntity<Page<EmailMessageModel>> findAllEmailMessagesIntoEmailChannel(@PathVariable String userName,
            @PathVariable UUID emailChannelId, SpecificationTemplate.EmailMessageSpec spec,
            @PageableDefault(page = 0, size = 10, sort = "chatMessageId", direction = Sort.Direction.DESC) Pageable pageable) {
        userService.findByUserName(userName);
        Page<EmailMessageModel> list = emailMessageService.findAllEmailMessagesIntoEmailChannel(SpecificationTemplate.emailMessageId(emailChannelId).and(spec), pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{userName}/emailchannels/{emailChannelId}/{emailMessageId}")
    public ResponseEntity<EmailMessageModel> findEmailMessageIntoEmailChannel(@PathVariable String userName,
            @PathVariable UUID emailChannelId, @PathVariable UUID emailMessageId) {
        userService.findByUserName(userName);
        EmailMessageModel obj = emailMessageService.findEmailMessageIntoEmailChannel(emailChannelId, emailMessageId);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/{userName}/emailchannels/{emailChannelId}")
    public ResponseEntity<EmailMessageModel> saveEmailMessage(@PathVariable String userName,
            @PathVariable UUID emailChannelId, @RequestBody @Valid EmailMessageDto emailMessageDto) {
        UserModel user = userService.findByUserName(userName);
        EmailChannelModel channel = emailChannelService.findEmailChannelIntoUser(user.getUserId(), emailChannelId);
        EmailMessageModel obj = emailMessageService.fromDto(emailMessageDto);
        obj = emailMessageService.insert(user, channel, obj);
        emailChannelService.save(channel);
        obj = emailMessageService.save(obj);
        sendEmailTask.sendMessage(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{emailMessageId}")
                .buildAndExpand(obj.getEmailMessageId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{userName}/emailchannels/{emailChannelId}/{emailMessageId}")
    public ResponseEntity<Object> deleteEmailMessage(@PathVariable String userName, @PathVariable UUID emailChannelId,
            @PathVariable UUID emailMessageId) {
        UserModel user = userService.findByUserName(userName);
        emailChannelService.findEmailChannelIntoUser(user.getUserId(), emailChannelId);
        emailMessageService.delete(emailMessageId);
        return ResponseEntity.ok().body("Mensagem deletado com sucesso");
    }
}
