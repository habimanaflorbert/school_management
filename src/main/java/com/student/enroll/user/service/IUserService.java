package com.student.enroll.user.service;

import java.util.Optional;

import com.student.enroll.user.model.User;
import com.student.enroll.user.request.AddUserRequest;

public interface IUserService {
    User createUser(AddUserRequest product);
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);

}
