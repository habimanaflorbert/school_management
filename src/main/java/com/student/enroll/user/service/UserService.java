package com.student.enroll.user.service;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.student.enroll.user.model.User;
import com.student.enroll.user.repository.UserRepository;
import com.student.enroll.user.request.AddUserRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service
@RequiredArgsConstructor
public class UserService implements IUserService{ 

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
    
    public User createUser(AddUserRequest user) {
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
            user.setIsActive(true); // default value
            user.setIsAdmin(false);// default value
            return userRepository.save(createAccount(user));
        }
        
        private User createAccount(AddUserRequest request){
            return new User(
                request.getFullName(),
                request.getEmail(),
                request.getUsername(),
                request.getPassword(),
                request.getIsActive(),
                request.getIsActive(),
                request.getIsFirstLogin(),
                request.getIsAdmin()
             
            );
        }
     
    
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean checkPassword(User user, String rawPassword) {
        return encoder.matches(rawPassword, user.getPassword());
    }  
    
}
