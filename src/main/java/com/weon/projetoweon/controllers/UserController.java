package com.weon.projetoweon.controllers;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonView;
import com.weon.projetoweon.dtos.UserDto;
import com.weon.projetoweon.models.UserModel;
import com.weon.projetoweon.services.UserService;
import com.weon.projetoweon.specifications.SpecificationTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserModel>> findAllUsers(SpecificationTemplate.UserSpec spec,
            @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<UserModel> userModelPage = userService.findAll(spec, pageable);
        return ResponseEntity.ok().body(userModelPage);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserModel> findUserById(@PathVariable UUID userId) {
        UserModel obj = userService.findById(userId);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID userId) {
        UserModel obj = userService.findById(userId);
        userService.delete(obj);
        return ResponseEntity.ok().body("Usuário deletado com sucesso");
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<UserModel> updateUser(@PathVariable UUID userId,
            @RequestBody @Validated(UserDto.UserView.UserPut.class) @JsonView(UserDto.UserView.UserPut.class) UserDto userDto) {
        UserModel obj = userService.fromDto(userDto);
        obj = userService.updateUser(userId, obj);
        obj = userService.save(obj);
        return ResponseEntity.ok().body(obj);
    }

}
