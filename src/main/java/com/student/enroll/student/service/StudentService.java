package com.student.enroll.student.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.enroll.exception.ResourceNotFoundException;
import com.student.enroll.student.model.Student;
import com.student.enroll.student.repository.StudentRepository;
import com.student.enroll.student.request.AddStudentRequest;
import com.student.enroll.student.request.UpdateStudentRequest;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    
    private final StudentRepository studentRepo;
    
    
    
    @Override
    public List<Student> getStudentByFirstName(String firstName) {
        return studentRepo.findByFirstName(firstName);
    }
    
    @Override
    public List<Student> getStudentByLastName(String lastName) {
        return studentRepo.findByLastName(lastName);
    }
    
    @Override
    public List<Student> getStudentByFirstNameAndLastName(String firstName, String lastName) {
        return studentRepo.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("No student found") );
    }
    
    
    @Override
    public void deleteStudentById(Long id) {
        studentRepo.findById(id).ifPresentOrElse(studentRepo:: delete,()->{new ResourceNotFoundException(" no student with this Id");});
    }
    
    @Override
    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }
    
    @Override
    public Student updateStudentById(UpdateStudentRequest request, Long id) {
       return studentRepo.findById(id)
       .map(existStudent->updateExistingStudent(existStudent, request))
       .map(studentRepo::save)
       .orElseThrow(()->new ResourceNotFoundException("Student not found !"));
    }
    private Student updateExistingStudent(Student existStudent,UpdateStudentRequest request){
        existStudent.setFirstName(request.getFirstName());
        existStudent.setLastName(request.getLastName());
        existStudent.setEmail(request.getEmail());
        existStudent.setPhone(request.getPhone());
        existStudent.setDateOfBirth(request.getDateOfBirth());
        return existStudent;
        
    }
    

    @Override
    public Student addStudent(AddStudentRequest student) {
     return studentRepo.save(createStudent(student));
    }
    private Student createStudent(AddStudentRequest student){
        return new Student(
            student.getFirstName(),
            student.getLastName(),
            student.getEmail(),
            student.getPhone(),
            student.getDateOfBirth()
        );
    }
}
