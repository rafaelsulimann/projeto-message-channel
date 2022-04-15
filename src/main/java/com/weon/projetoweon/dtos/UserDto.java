package com.weon.projetoweon.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.weon.projetoweon.validations.UsernameConstraint;

public class UserDto implements Serializable{

    private static final long serialVersionUID = 1L;

    public interface UserView{
        public static interface RegistrationPost{}
        public static interface UserPut{}
    }

    @Size(min = 4, max = 50, groups = UserView.RegistrationPost.class)
    @UsernameConstraint(groups = UserView.RegistrationPost.class)   
    @JsonView(UserView.RegistrationPost.class)
    private String userName;

    @NotBlank(groups = {UserView.RegistrationPost.class, UserView.UserPut.class})   
    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String fullName;

    @NotBlank(groups = {UserView.RegistrationPost.class, UserView.UserPut.class})  
    @Email(groups = {UserView.RegistrationPost.class, UserView.UserPut.class})
    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String email;

    @Size(min = 9, max = 11, groups = {UserView.RegistrationPost.class, UserView.UserPut.class})
    @NotBlank(groups = {UserView.RegistrationPost.class, UserView.UserPut.class})
    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String phone;

    public UserDto(){
    }

    public UserDto(String userName, String fullName, String email, String phone) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }    
    
}
