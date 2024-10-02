package com.student.enroll.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.student.enroll.user.model.User;
import com.student.enroll.user.repository.UserPrincipal;
import com.student.enroll.user.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
	 private UserRepository userRepository;
     
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findByUsername(username);
	
	if (user==null) {
		System.out.println("User 404");
		throw new UsernameNotFoundException("User 404");
	}
		 return new UserPrincipal(user);
	}
}
