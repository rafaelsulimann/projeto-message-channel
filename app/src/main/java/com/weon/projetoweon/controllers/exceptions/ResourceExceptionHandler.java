package com.weon.projetoweon.controllers.exceptions;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.http.HttpServletRequest;

import com.weon.projetoweon.services.exceptions.ChatChannelNotFoundException;
import com.weon.projetoweon.services.exceptions.ChatMessageNotFoundException;
import com.weon.projetoweon.services.exceptions.EmailChannelNotFoundException;
import com.weon.projetoweon.services.exceptions.EmailMessageNotFoundException;
import com.weon.projetoweon.services.exceptions.ExistsByEmailException;
import com.weon.projetoweon.services.exceptions.ExistsByPhoneException;
import com.weon.projetoweon.services.exceptions.ExistsByUserNameException;
import com.weon.projetoweon.services.exceptions.UserNotFoundException;
import com.weon.projetoweon.services.exceptions.VoiceChannelNotFoundException;
import com.weon.projetoweon.services.exceptions.VoiceMessageNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandardError> userNotFound(UserNotFoundException e, HttpServletRequest request){
        String error = "Usuário não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ChatChannelNotFoundException.class)
    public ResponseEntity<StandardError> chatChannelNotFound(ChatChannelNotFoundException e, HttpServletRequest request){
        String error = "Chat Channel não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ChatMessageNotFoundException.class)
    public ResponseEntity<StandardError> chatMessageNotFound(ChatMessageNotFoundException e, HttpServletRequest request){
        String error = "Chat Message não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EmailChannelNotFoundException.class)
    public ResponseEntity<StandardError> emailChannelNotFound(EmailChannelNotFoundException e, HttpServletRequest request){
        String error = "Emal Channel não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EmailMessageNotFoundException.class)
    public ResponseEntity<StandardError> emailMessageNotFound(EmailMessageNotFoundException e, HttpServletRequest request){
        String error = "Email Message não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(VoiceChannelNotFoundException.class)
    public ResponseEntity<StandardError> voiceChannelNotFound(VoiceChannelNotFoundException e, HttpServletRequest request){
        String error = "Voice Channel não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(VoiceMessageNotFoundException.class)
    public ResponseEntity<StandardError> voiceMessageNotFound(VoiceMessageNotFoundException e, HttpServletRequest request){
        String error = "Voice Message não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ExistsByUserNameException.class)
    public ResponseEntity<StandardError> existsByUserName(ExistsByUserNameException e, HttpServletRequest request){
        String error = "Username já existente";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ExistsByPhoneException.class)
    public ResponseEntity<StandardError> existsByPhone(ExistsByPhoneException e, HttpServletRequest request){
        String error = "Telefone já existente";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ExistsByEmailException.class)
    public ResponseEntity<StandardError> existsByEmail(ExistsByEmailException e, HttpServletRequest request){
        String error = "Email já existente";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    
}
