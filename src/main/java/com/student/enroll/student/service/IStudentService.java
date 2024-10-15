package com.student.enroll.student.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.enroll.student.model.Student;
import com.student.enroll.student.request.AddStudentRequest;
import com.student.enroll.student.request.UpdateStudentRequest;

@Service
public interface IStudentService {

    Student addStudent(AddStudentRequest student);
    
    List<Student> getStudentByFirstName(String firstName);

    List<Student> getAllStudent();

    List<Student> getStudentByLastName(String lastName);

    List<Student> getStudentByFirstNameAndLastName(String firstName, String lastName);

    Student getStudentById(Long id);

    Student updateStudentById(UpdateStudentRequest request, Long id);

    void deleteStudentById(Long id);

}
