package com.student.enroll.student.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.enroll.classRoom.model.ClassRoom;
import com.student.enroll.classRoom.repository.ClassRoomRepository;
import com.student.enroll.exception.ResourceNotFoundException;
import com.student.enroll.student.model.Student;
import com.student.enroll.student.model.StudentClassRoom;
import com.student.enroll.student.repository.StudentClassRoomRepository;
import com.student.enroll.student.repository.StudentRepository;
import com.student.enroll.student.request.AddStudentClassRoomRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentClassRoomService implements IStudentClassRoomService {

    private final StudentClassRoomRepository studentClassRoomRepository;
    private final StudentRepository studentRepository;
    private final ClassRoomRepository classRoomRepository;
    


    @Override
    public List<StudentClassRoom> getAllStudentClassRoom() {
     return studentClassRoomRepository.findAll();
    }
    
    @Override
    public List<StudentClassRoom> getStudentClassRoomByStudent(Long student) {
        return studentClassRoomRepository.findByStudentId(student);
    }
    
    @Override
    public List<StudentClassRoom> getClassRoom(Long classRoom) {
       return studentClassRoomRepository.findByClassRoomId(classRoom);
    }

    @Override
    public StudentClassRoom addStudentClassRoom(AddStudentClassRoomRequest request) {
       
        Student student=studentRepository.findById(request.getStudent()).orElseThrow(() -> new ResourceNotFoundException("student not Found!"));
        ClassRoom classRoom=classRoomRepository.findById(request.getClassRoom()).orElseThrow(() -> new ResourceNotFoundException("Class Room not Found!"));
      return studentClassRoomRepository.save(new StudentClassRoom(student,classRoom));
    }
 
    
    
    @Override
    public StudentClassRoom updateStudentClassRoomById(AddStudentClassRoomRequest request, Long id) {
        Student student=studentRepository.findById(request.getStudent()).orElseThrow(() ->  new ResourceNotFoundException("student not Found!"));
        ClassRoom classRoom=classRoomRepository.findById(request.getClassRoom()).orElseThrow(() -> new ResourceNotFoundException("Class Room not Found!"));
        return studentClassRoomRepository.findById(id)
        .map(existingStudentClassRoom -> new StudentClassRoom(student,classRoom))
        .map(studentClassRoomRepository::save)
        .orElseThrow(()-> new ResourceNotFoundException("class room not found !"));
    }
   

    @Override
    public void deleteStudentById(Long id) {
        studentClassRoomRepository.findById(id).ifPresentOrElse(studentClassRoomRepository::delete,()-> {new ResourceNotFoundException("Class Room not Found!");});

    }
    
}
