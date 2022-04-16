package com.weon.projetoweon.services;

import java.util.UUID;

import com.weon.projetoweon.dtos.UserDto;
import com.weon.projetoweon.models.UserModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface UserService {

    public Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable);
    public UserModel findById(UUID userId);
    public UserModel findByUserName(String userName);
    public UserModel findByEmail(String email);
    public UserModel findByPhone(String phone);
    public void delete(UserModel obj);
    public UserModel save(UserModel obj);
    public UserModel insert(UserModel obj);
    public UserModel updateUser(UUID userId, UserModel obj);
    public UserModel fromDto(UserDto userDto);
    public boolean existsByUserName(String userName);
    public boolean existsByEmail(String email);
    public boolean existsByPhone(String phone);
    
}
