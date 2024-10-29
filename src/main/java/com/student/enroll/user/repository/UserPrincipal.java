package com.student.enroll.user.repository;


import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.student.enroll.user.model.User;

import java.util.*;

public class UserPrincipal  implements UserDetails{
    private User user;

    public UserPrincipal(User user){
        this.user=user;
    }
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
       return user.getPassword();
    }
    
   
    public Long getId() {
       return user.getId();
    }
    @Override
    public String getUsername() {
       return user.getUsername();
    }

       
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
