package com.weon.projetoweon.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import com.weon.projetoweon.dtos.EmailChannelDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.email.EmailChannelModel;
import com.weon.projetoweon.services.EmailChannelService;
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
public class EmailChannelController {

    @Autowired
    private EmailChannelService emailChannelService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{userName}/emailchannels")
    public ResponseEntity<Page<EmailChannelModel>> findAllEmailsChannelsIntoUser(@PathVariable String userName,
            SpecificationTemplate.EmailChannelSpec spec,
            @Qualifier("EmailChannelController")@PageableDefault(page = 0, size = 10, sort = "emailChannelId", direction = Sort.Direction.ASC) Pageable pageable,
            SpecificationTemplate.EmailMessageSpec messageSpec,
            @Qualifier("EmailMessageController")@PageableDefault(page = 0, size = 10, sort = "emailMessageId", direction = Sort.Direction.ASC) Pageable messagePageable) {
        UserModel user = userService.findByUserName(userName);
        Page<EmailChannelModel> list = emailChannelService.findAllEmailsChannelsIntoUser(
                SpecificationTemplate.emailChannelId(user.getUserName()).and(spec), pageable);
        if (!list.isEmpty()) {
            for (EmailChannelModel channel : list) {
                if (channel.getLastEmailMessage() != null) {
                    channel.add(
                            linkTo(methodOn(EmailMessageController.class).findAllEmailMessagesIntoEmailChannel(userName,
                                    channel.getEmailChannelId(), messageSpec,
                                    messagePageable)).withSelfRel());
                }
            }
        }
        return ResponseEntity.ok().body(list);
    }

    public ResponseEntity<EmailChannelModel> findEmailChannelIntoUser(@PathVariable String userName,
            @PathVariable UUID emailChannelId) {
        UserModel user = userService.findByUserName(userName);
        EmailChannelModel obj = emailChannelService.findEmailChannelIntoUser(user.getUserId(), emailChannelId);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/{userName}/emailchannels")
    public ResponseEntity<EmailChannelModel> saveEmailChannel(@PathVariable String userName,
            @RequestBody @Valid EmailChannelDto emailChannelDto) {
        UserModel user = userService.findByUserName(userName);
        EmailChannelModel obj = emailChannelService.fromDto(emailChannelDto);
        if (!userService.existsByEmail(obj.getToEmail())) {
            throw new UserNotFoundException(obj.getToEmail());
        }
        obj = emailChannelService.insert(user, obj);
        obj = emailChannelService.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{emailChannelId}")
                .buildAndExpand(obj.getEmailChannelId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{userName}/emailchannels/{emailChannelId}")
    public ResponseEntity<Object> deleteEmailChannel(@PathVariable String userName, @PathVariable UUID emailChannelId) {
        ResponseEntity<EmailChannelModel> obj = findEmailChannelIntoUser(userName, emailChannelId);
        emailChannelService.delete(obj.getBody());
        return ResponseEntity.ok().body("Email Channel deletado com sucesso");
    }

}
