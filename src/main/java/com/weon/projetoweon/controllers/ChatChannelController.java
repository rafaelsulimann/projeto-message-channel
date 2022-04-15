package com.weon.projetoweon.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import com.weon.projetoweon.dtos.ChatChannelDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.chat.ChatChannelModel;
import com.weon.projetoweon.services.ChatChannelService;
import com.weon.projetoweon.services.UserService;
import com.weon.projetoweon.services.exceptions.UserNotFoundException;
import com.weon.projetoweon.specifications.SpecificationTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ChatChannelController {

    @Autowired
    private ChatChannelService chatChannelService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{userName}/chatchannels")
    public ResponseEntity<Page<ChatChannelModel>> findAllChatsChannelsIntoUser(@PathVariable String userName,
            SpecificationTemplate.ChatChannelSpec spec,
            @Qualifier("ChatChannelController")@PageableDefault(page = 0, size = 10, sort = "chatChannelId", direction = Sort.Direction.ASC) Pageable pageable,
            SpecificationTemplate.ChatMessageSpec messageSpec,
            @Qualifier("ChatMessageController")@PageableDefault(page = 0, size = 10, sort = "chatMessageId", direction = Sort.Direction.ASC)Pageable messagePageable) {
        UserModel user = userService.findByUserName(userName);
        Page<ChatChannelModel> list = chatChannelService.findAllChatsChannelsIntoUser(
                SpecificationTemplate.chatChannelId(user.getUserName()).and(spec), pageable);
        if (!list.isEmpty()) {
            for (ChatChannelModel channel : list) {
                if (channel.getLastChatMessage() != null) {
                    channel.add(linkTo(methodOn(ChatMessageController.class)
                            .findAllChatMessagesIntoChatChannel(userName, channel.getChatChannelId(), messageSpec,
                                    messagePageable))
                            .withSelfRel());
                }
            }
        }
        return ResponseEntity.ok().body(list);
    }

    public ResponseEntity<ChatChannelModel> findChatChannelIntoUser(@PathVariable String userName,
            @PathVariable UUID chatChannelId) {
        UserModel user = userService.findByUserName(userName);
        ChatChannelModel obj = chatChannelService.findChatChannelIntoUser(user.getUserId(), chatChannelId);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/{userName}/chatchannels")
    public ResponseEntity<ChatChannelModel> saveChatChannel(@PathVariable String userName,
            @RequestBody @Valid ChatChannelDto chatChannelDto) {
        UserModel user = userService.findByUserName(userName);
        ChatChannelModel obj = chatChannelService.fromDto(chatChannelDto);
        if (!userService.existsByUserName(obj.getToUser())) {
            throw new UserNotFoundException(obj.getToUser());
        }
        obj = chatChannelService.insert(user, obj);
        obj = chatChannelService.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{chatChannelId}")
                .buildAndExpand(obj.getChatChannelId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{userName}/chatchannels/{chatChannelId}")
    public ResponseEntity<Object> deleteChatChannel(@PathVariable String userName, @PathVariable UUID chatChannelId) {
        ResponseEntity<ChatChannelModel> obj = findChatChannelIntoUser(userName, chatChannelId);
        chatChannelService.delete(obj.getBody());
        return ResponseEntity.ok().body("Chat Channel deletado com sucesso");
    }

}
