package com.student.enroll.user.request;

import lombok.Data;

@Data
public class AddUserRequest {
    private Long id;
    private String fullName;
    private String email;
    private String username;
    private String password;
    private Boolean isActive = false;
    private Boolean isFirstLogin=false;
    private Boolean isAdmin=false;
}
