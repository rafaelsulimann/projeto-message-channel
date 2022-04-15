package com.weon.projetoweon.specifications;

import java.util.Collection;
import java.util.UUID;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.models.channels.chat.ChatChannelModel;
import com.weon.projetoweon.models.channels.chat.ChatMessageModel;
import com.weon.projetoweon.models.channels.email.EmailChannelModel;
import com.weon.projetoweon.models.channels.email.EmailMessageModel;
import com.weon.projetoweon.models.channels.voice.VoiceChannelModel;
import com.weon.projetoweon.models.channels.voice.VoiceMessageModel;

import org.springframework.data.jpa.domain.Specification;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

public class SpecificationTemplate {

    @And({
        @Spec(path = "userName", spec = Like.class),
        @Spec(path = "email", spec = Like.class),
        @Spec(path = "phone", spec = Like.class)
    })
    public interface UserSpec extends Specification<UserModel>{}

    @And({
        @Spec(path = "messageStatus", spec = Equal.class),
        @Spec(path = "message", spec = Like.class)
    })
    public interface ChatMessageSpec extends Specification<ChatMessageModel>{}

    @And({
        @Spec(path = "messageStatus", spec = Equal.class),
        @Spec(path = "message", spec = Like.class)
    })
    public interface EmailMessageSpec extends Specification<EmailMessageModel>{}

    @And({
        @Spec(path = "messageStatus", spec = Equal.class),
        @Spec(path = "message", spec = Like.class)
    })
    public interface VoiceMessageSpec extends Specification<VoiceMessageModel>{}
    
    @Spec(path = "toUser", spec = Like.class)
    public interface ChatChannelSpec extends Specification<ChatChannelModel>{}
    
    @Spec(path = "toEmail", spec = Like.class)
    public interface EmailChannelSpec extends Specification<EmailChannelModel>{}

    @Spec(path = "toPhone", spec = Like.class)
    public interface VoiceChannelSpec extends Specification<VoiceChannelModel>{}

    public static Specification<ChatChannelModel> chatChannelId(final String userName){
        return(root, query, cb) -> {
            query.distinct(true);
            Root<ChatChannelModel> chatChannel = root;
            Root<UserModel> user = query.from(UserModel.class);
            Expression<Collection<ChatChannelModel>> userChatChannels = user.get("chatChannels");
            return cb.and(cb.equal(user.get("userName"), userName), cb.isMember(chatChannel, userChatChannels));
        };
    }

    public static Specification<EmailChannelModel> emailChannelId(final String userName){
        return(root, query, cb) -> {
            query.distinct(true);
            Root<EmailChannelModel> emailChannel = root;
            Root<UserModel> user = query.from(UserModel.class);
            Expression<Collection<EmailChannelModel>> userEmailChannels = user.get("emailChannels");
            return cb.and(cb.equal(user.get("userName"), userName), cb.isMember(emailChannel, userEmailChannels));
        };
    }

    public static Specification<VoiceChannelModel> voiceChannelId(final String userName){
        return(root, query, cb) -> {
            query.distinct(true);
            Root<VoiceChannelModel> voiceChannel = root;
            Root<UserModel> user = query.from(UserModel.class);
            Expression<Collection<VoiceChannelModel>> userVoiceChannels = user.get("voiceChannels");
            return cb.and(cb.equal(user.get("userName"), userName), cb.isMember(voiceChannel, userVoiceChannels));
        };
    }

    public static Specification<ChatMessageModel> chatMessageId(final UUID chatChannelId){
        return(root, query, cb) -> {
            query.distinct(true);
            Root<ChatMessageModel> chatMessage = root;
            Root<ChatChannelModel> chatChannel = query.from(ChatChannelModel.class);
            Expression<Collection<ChatMessageModel>> channelChatMessages = chatChannel.get("chatMessages");
            return cb.and(cb.equal(chatChannel.get("chatChannelId"), chatChannelId), cb.isMember(chatMessage, channelChatMessages));
        };
    }

    public static Specification<EmailMessageModel> emailMessageId(final UUID emailChannelId){
        return(root, query, cb) -> {
            query.distinct(true);
            Root<EmailMessageModel> emailMessage = root;
            Root<EmailChannelModel> emailChannel = query.from(EmailChannelModel.class);
            Expression<Collection<EmailMessageModel>> channelEmailMessages = emailChannel.get("emailMessages");
            return cb.and(cb.equal(emailChannel.get("emailChannelId"), emailChannelId), cb.isMember(emailMessage, channelEmailMessages));
        };
    }

    public static Specification<VoiceMessageModel> voiceMessageId(final UUID voiceChannelId){
        return(root, query, cb) -> {
            query.distinct(true);
            Root<VoiceMessageModel> voiceMessage = root;
            Root<VoiceChannelModel> voiceChannel = query.from(VoiceChannelModel.class);
            Expression<Collection<VoiceMessageModel>> channelVoiceMessages = voiceChannel.get("voiceMessages");
            return cb.and(cb.equal(voiceChannel.get("voiceChannelId"), voiceChannelId), cb.isMember(voiceMessage, channelVoiceMessages));
        };
    }
    
}
