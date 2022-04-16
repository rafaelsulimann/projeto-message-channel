package com.weon.projetoweon.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import com.weon.projetoweon.dtos.VoiceChannelDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.voice.VoiceChannelModel;
import com.weon.projetoweon.services.UserService;
import com.weon.projetoweon.services.VoiceChannelService;
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
public class VoiceChannelController {

    @Autowired
    private VoiceChannelService voiceChannelService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{userName}/voicechannels")
    public ResponseEntity<Page<VoiceChannelModel>> findAllVoicesChannelsIntoUser(@PathVariable String userName,
            SpecificationTemplate.VoiceChannelSpec spec,
            @Qualifier("VoiceChannelController")@PageableDefault(page = 0, size = 10, sort = "voiceChannelId", direction = Sort.Direction.ASC) Pageable pageable,
            SpecificationTemplate.VoiceMessageSpec messageSpec,
            @Qualifier("VoiceMessageController")@PageableDefault(page = 0, size = 10, sort = "voiceMessageId", direction = Sort.Direction.ASC) Pageable messagePageable) {
        UserModel user = userService.findByUserName(userName);
        Page<VoiceChannelModel> list = voiceChannelService.findAllVoicesChannelsIntoUser(
                SpecificationTemplate.voiceChannelId(user.getUserName()).and(spec), pageable);
        if (!list.isEmpty()) {
            for (VoiceChannelModel channel : list) {
                if (channel.getLastVoiceMessage() != null) {
                    channel.add(
                            linkTo(methodOn(VoiceMessageController.class).findAllVoiceMessagesIntoVoiceChannel(userName,
                                    channel.getVoiceChannelId(), messageSpec, messagePageable)).withSelfRel());
                }
            }
        }
        return ResponseEntity.ok().body(list);
    }

    public ResponseEntity<VoiceChannelModel> findVoiceChannelIntoUser(@PathVariable String userName,
            @PathVariable UUID voiceChannelId) {
        UserModel user = userService.findByUserName(userName);
        VoiceChannelModel obj = voiceChannelService.findVoiceChannelIntoUser(user.getUserId(), voiceChannelId);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/{userName}/voicechannels")
    public ResponseEntity<VoiceChannelModel> saveVoiceChannel(@PathVariable String userName,
            @RequestBody @Valid VoiceChannelDto voiceChannelDto) {
        UserModel user = userService.findByUserName(userName);
        VoiceChannelModel obj = voiceChannelService.fromDto(voiceChannelDto);
        if (!userService.existsByPhone(obj.getToPhone())) {
            throw new UserNotFoundException(obj.getToPhone());
        }
        obj = voiceChannelService.insert(user, obj);
        obj = voiceChannelService.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{voiceChannelId}")
                .buildAndExpand(obj.getVoiceChannelId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{userName}/voicechannels/{voiceChannelId}")
    public ResponseEntity<Object> deleteVoiceChannel(@PathVariable String userName, @PathVariable UUID voiceChannelId) {
        ResponseEntity<VoiceChannelModel> obj = findVoiceChannelIntoUser(userName, voiceChannelId);
        voiceChannelService.delete(obj.getBody());
        return ResponseEntity.ok().body("Voice Channel deletado com sucesso");
    }

}
