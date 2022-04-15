package com.weon.projetoweon.controllers;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonView;
import com.weon.projetoweon.dtos.UserDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.services.UserService;
import com.weon.projetoweon.services.exceptions.ExistsByEmailException;
import com.weon.projetoweon.services.exceptions.ExistsByPhoneException;
import com.weon.projetoweon.services.exceptions.ExistsByUserNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;
    
    @PostMapping(value = "/signup")
    public ResponseEntity<UserModel> registerUser(@RequestBody @Validated (UserDto.UserView.RegistrationPost.class) @JsonView (UserDto.UserView.RegistrationPost.class) UserDto userDto){
        if (userService.existsByUserName(userDto.getUserName())) {
            throw new ExistsByUserNameException(userDto.getUserName());
        }
        if (userService.existsByEmail(userDto.getEmail())) {
            throw new ExistsByEmailException(userDto.getEmail());
        }
        if (userService.existsByPhone(userDto.getPhone())) {
            throw new ExistsByPhoneException(userDto.getPhone());
        }
        UserModel obj = userService.fromDto(userDto);
        obj = userService.insert(obj);
        obj = userService.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(obj.getUserId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
}
