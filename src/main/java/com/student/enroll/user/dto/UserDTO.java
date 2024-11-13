package com.student.enroll.user.dto;

import java.util.List;

import com.student.enroll.user.model.User.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private List<Role> authorities;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
}


