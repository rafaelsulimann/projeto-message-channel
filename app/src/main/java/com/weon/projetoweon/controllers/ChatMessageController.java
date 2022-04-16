package com.weon.projetoweon.controllers;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import com.weon.projetoweon.dtos.ChatMessageDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.chat.ChatChannelModel;
import com.weon.projetoweon.models.channels.chat.ChatMessageModel;
import com.weon.projetoweon.services.ChatChannelService;
import com.weon.projetoweon.services.ChatMessageService;
import com.weon.projetoweon.services.UserService;
import com.weon.projetoweon.specifications.SpecificationTemplate;
import com.weon.projetoweon.tasks.SendMessageTask;

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
public class ChatMessageController {

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private ChatChannelService chatChannelService;

    @Autowired
    private UserService userService;

    @Autowired
    private SendMessageTask sendMessageTask;

    @GetMapping(value = "/{userName}/chatchannels/{chatChannelId}")
    public ResponseEntity<Page<ChatMessageModel>> findAllChatMessagesIntoChatChannel(@PathVariable String userName,
            @PathVariable UUID chatChannelId, SpecificationTemplate.ChatMessageSpec spec,
            @PageableDefault(page = 0, size = 10, sort = "chatMessageId", direction = Sort.Direction.DESC) Pageable pageable) {
        userService.findByUserName(userName);
        Page<ChatMessageModel> list = chatMessageService.findAllChatMessagesIntoChatChannel(SpecificationTemplate.chatMessageId(chatChannelId).and(spec), pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{userName}/chatchannels/{chatChannelId}/{chatMessageId}")
    public ResponseEntity<ChatMessageModel> findChatMessageIntoChatChannel(@PathVariable String userName,
            @PathVariable UUID chatChannelId, @PathVariable UUID chatMessageId) {
        userService.findByUserName(userName);
        ChatMessageModel obj = chatMessageService.findChatMessageIntoChatChannel(chatChannelId, chatMessageId);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/{userName}/chatchannels/{chatChannelId}")
    public ResponseEntity<ChatMessageModel> saveChatMessage(@PathVariable String userName,
            @PathVariable UUID chatChannelId, @RequestBody @Valid ChatMessageDto chatMessageDto) {
        UserModel user = userService.findByUserName(userName);
        ChatChannelModel channel = chatChannelService.findChatChannelIntoUser(user.getUserId(), chatChannelId);
        ChatMessageModel obj = chatMessageService.fromDto(chatMessageDto);
        obj = chatMessageService.insert(user, channel, obj);
        chatChannelService.save(channel);
        obj = chatMessageService.save(obj);
        sendMessageTask.sendMessage(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{chatMessageId}")
                .buildAndExpand(obj.getChatMessageId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{userName}/chatchannels/{chatChannelId}/{chatMessageId}")
    public ResponseEntity<Object> deleteChatMessage(@PathVariable String userName, @PathVariable UUID chatChannelId,
            @PathVariable UUID chatMessageId) {
        UserModel user = userService.findByUserName(userName);
        chatChannelService.findChatChannelIntoUser(user.getUserId(), chatChannelId);
        chatMessageService.delete(chatMessageId);
        return ResponseEntity.ok().body("Mensagem deletado com sucesso");
    }
}
