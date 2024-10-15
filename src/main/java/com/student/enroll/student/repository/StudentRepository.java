package com.student.enroll.student.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.student.enroll.student.model.Student;

import java.util.List;



public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByFirstName(String firstName);
    List<Student> findByLastName(String lastName);
    List<Student> findByFirstNameAndLastName(String firstName,String lastName);
    
    
}
