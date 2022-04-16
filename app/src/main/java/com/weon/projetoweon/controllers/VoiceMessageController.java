package com.weon.projetoweon.controllers;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import com.weon.projetoweon.dtos.VoiceMessageDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.voice.VoiceChannelModel;
import com.weon.projetoweon.models.channels.voice.VoiceMessageModel;
import com.weon.projetoweon.services.UserService;
import com.weon.projetoweon.services.VoiceChannelService;
import com.weon.projetoweon.services.VoiceMessageService;
import com.weon.projetoweon.specifications.SpecificationTemplate;
import com.weon.projetoweon.tasks.SendVoiceTask;

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
public class VoiceMessageController {

    @Autowired
    private VoiceMessageService voiceMessageService;

    @Autowired
    private VoiceChannelService voiceChannelService;

    @Autowired
    private UserService userService;

    @Autowired
    private SendVoiceTask sendVoiceTask;

    @GetMapping(value = "/{userName}/voicechannels/{voiceChannelId}")
    public ResponseEntity<Page<VoiceMessageModel>> findAllVoiceMessagesIntoVoiceChannel(@PathVariable String userName,
            @PathVariable UUID voiceChannelId, SpecificationTemplate.VoiceMessageSpec spec,
            @PageableDefault(page = 0, size = 10, sort = "voiceMessageId", direction = Sort.Direction.ASC) Pageable pageable) {
        userService.findByUserName(userName);
        Page<VoiceMessageModel> list = voiceMessageService.findAllVoiceMessagesIntoVoiceChannel(SpecificationTemplate.voiceMessageId(voiceChannelId).and(spec), pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{userName}/voicechannels/{voiceChannelId}/{voiceMessageId}")
    public ResponseEntity<VoiceMessageModel> findVoiceMessageIntoVoiceChannel(@PathVariable String userName,
            @PathVariable UUID voiceChannelId, @PathVariable UUID voiceMessageId) {
        userService.findByUserName(userName);
        VoiceMessageModel obj = voiceMessageService.findVoiceMessageIntoVoiceChannel(voiceChannelId, voiceMessageId);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/{userName}/voicechannels/{voiceChannelId}")
    public ResponseEntity<VoiceMessageModel> saveVoiceMessage(@PathVariable String userName,
            @PathVariable UUID voiceChannelId, @RequestBody @Valid VoiceMessageDto voiceMessageDto) {
        UserModel user = userService.findByUserName(userName);
        VoiceChannelModel channel = voiceChannelService.findVoiceChannelIntoUser(user.getUserId(), voiceChannelId);
        VoiceMessageModel obj = voiceMessageService.fromDto(voiceMessageDto);
        obj = voiceMessageService.insert(user, channel, obj);
        voiceChannelService.save(channel);
        obj = voiceMessageService.save(obj);
        sendVoiceTask.sendMessage(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{voiceMessageId}")
                .buildAndExpand(obj.getVoiceMessageId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{userName}/voicechannels/{voiceChannelId}/{voiceMessageId}")
    public ResponseEntity<Object> deleteVoiceMessage(@PathVariable String userName, @PathVariable UUID voiceChannelId,
            @PathVariable UUID voiceMessageId) {
        UserModel user = userService.findByUserName(userName);
        voiceChannelService.findVoiceChannelIntoUser(user.getUserId(), voiceChannelId);
        voiceMessageService.delete(voiceMessageId);
        return ResponseEntity.ok().body("Mensagem deletado com sucesso");
    }
}
