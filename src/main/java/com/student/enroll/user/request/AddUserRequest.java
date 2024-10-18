package com.student.enroll.user.request;

import javax.validation.constraints.NotNull;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AddUserRequest {
    @NotBlank(message = "fullName cannot be empty")
    @NotNull(message = "fullName cannot be null")
    private String fullName;
    @Email(message = "should be email please")
    @NotBlank(message = "email cannot be empty")
    private String email;
    @NotBlank(message = "username cannot be empty")
    @NotNull(message = "username cannot be null")
    private String username;
    @NotNull(message = "Password cannot be null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", 
             message = "Password must be at least 8 characters long, contain at least one digit, one uppercase letter, one lowercase letter, and one special character")
    private String password;
    private Boolean isActive = false;
    private Boolean isFirstLogin=false;
    private Boolean isAdmin=false;
}
