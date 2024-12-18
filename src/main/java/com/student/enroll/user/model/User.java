package com.student.enroll.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String username;
    @JsonIgnore  
    private String password;
    private Boolean isActive = false;
    private Boolean isFirstLogin = false;
    private Boolean isAdmin = false;

    

    public User(String fullName, String username, String email, String password, Boolean isActive, Boolean isFirstLogin,
        Boolean isAdmin, Boolean boolean1) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.isFirstLogin = isFirstLogin;
        this.isAdmin = isAdmin;
    }


    public enum Role {
        USER,
        ADMIN,
        MODERATOR;
    }
    
}
