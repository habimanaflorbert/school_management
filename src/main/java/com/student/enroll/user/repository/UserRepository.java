package com.student.enroll.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.enroll.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    Optional<User> findByEmail(String email);
}
