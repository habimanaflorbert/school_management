package com.student.enroll.user.service;

import com.student.enroll.user.model.User;
import com.student.enroll.user.request.AddUserRequest;

public interface IUserService {
    User createUser(AddUserRequest product);
}
