package com.weon.projetoweon.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.weon.projetoweon.dtos.UserDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.chat.ChatChannelModel;
import com.weon.projetoweon.models.channels.chat.ChatMessageModel;
import com.weon.projetoweon.models.channels.email.EmailChannelModel;
import com.weon.projetoweon.models.channels.email.EmailMessageModel;
import com.weon.projetoweon.models.channels.voice.VoiceChannelModel;
import com.weon.projetoweon.models.channels.voice.VoiceMessageModel;
import com.weon.projetoweon.repositories.ChatChannelRepository;
import com.weon.projetoweon.repositories.ChatMessageRepository;
import com.weon.projetoweon.repositories.EmailChannelRepository;
import com.weon.projetoweon.repositories.EmailMessageRepository;
import com.weon.projetoweon.repositories.UserRepository;
import com.weon.projetoweon.repositories.VoiceChannelRepository;
import com.weon.projetoweon.repositories.VoiceMessageRepository;
import com.weon.projetoweon.services.UserService;
import com.weon.projetoweon.services.exceptions.UserNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatChannelRepository chatChannelRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private EmailChannelRepository emailChannelRepository;

    @Autowired
    private EmailMessageRepository emailMessageRepository;

    @Autowired
    private VoiceChannelRepository voiceChannelRepository;

    @Autowired
    private VoiceMessageRepository voiceMessageRepository;

    @Override
    public Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable) {
        return userRepository.findAll(spec, pageable);        
    }

    @Override
    public UserModel findById(UUID userId) {
        Optional<UserModel> obj = userRepository.findById(userId);
        return obj.orElseThrow(() -> new UserNotFoundException(userId));       
    }

    @Override
    public UserModel findByUserName(String userName){
        Optional<UserModel> obj = userRepository.findByUserName(userName);
        return obj.orElseThrow(() -> new UserNotFoundException(userName));
    }

    @Override
    public UserModel findByEmail(String email){
        Optional<UserModel> obj = userRepository.findByEmail(email);
        return obj.orElseThrow(() -> new UserNotFoundException(email));
    }
    @Override
    public UserModel findByPhone(String phone){
        Optional<UserModel> obj = userRepository.findByPhone(phone);
        return obj.orElseThrow(() -> new UserNotFoundException(phone));
    }

    @Override
    public UserModel insert(UserModel obj) {
        obj.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        obj.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return obj;
    }

    @Override
    public UserModel save(UserModel obj){
        return userRepository.save(obj);
    }

    @Override
    public UserModel updateUser(UUID userId, UserModel obj) {
        UserModel entity = findById(userId);
        entity.setFullName(obj.getFullName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
        entity.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return entity;
    }

    @Override
    public UserModel fromDto(UserDto userDto) {
        UserModel obj = new UserModel();
        BeanUtils.copyProperties(userDto, obj);
        return obj;
    }     

    @Transactional
    @Override
    public void delete(UserModel obj) {
        List<ChatChannelModel> chatChannelList = chatChannelRepository.findAllChatsChannelsIntoUser(obj.getUserId());
        if(!chatChannelList.isEmpty()){
            for(ChatChannelModel chatChannel : chatChannelList){
                List<ChatMessageModel> chatMessageList = chatMessageRepository.findAllChatMessagesIntoChatChannel(chatChannel.getChatChannelId());
                if(!chatChannelList.isEmpty()){
                    chatMessageRepository.deleteAll(chatMessageList);
                }
            }
            chatChannelRepository.deleteAll(chatChannelList);
        }
        List<EmailChannelModel> emailChannelList = emailChannelRepository.findAllEmailsChannelsIntoUser(obj.getUserId());
        if(!emailChannelList.isEmpty()){
            for(EmailChannelModel emailChannel : emailChannelList){
                List<EmailMessageModel> emailMessageList = emailMessageRepository.findAllEmailMessagesIntoEmailChannel(emailChannel.getEmailChannelId());
                if(!emailMessageList.isEmpty()){
                    emailMessageRepository.deleteAll(emailMessageList);
                }
            }
            emailChannelRepository.deleteAll(emailChannelList);
        }
        List<VoiceChannelModel> voiceChannelList = voiceChannelRepository.findAllVoicesChannelsIntoUser(obj.getUserId());
        if(!voiceChannelList.isEmpty()){
            for(VoiceChannelModel voiceChannel : voiceChannelList){
                List<VoiceMessageModel> voiceMessageList = voiceMessageRepository.findAllVoiceMessagesIntoVoiceChannel(voiceChannel.getVoiceChannelId());
                if(!voiceMessageList.isEmpty()){
                    voiceMessageRepository.deleteAll(voiceMessageList);
                }
            }
            voiceChannelRepository.deleteAll(voiceChannelList);
        }
        userRepository.delete(obj);        
    }

    @Override
    public boolean existsByUserName(String userName){
        return userRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

}
