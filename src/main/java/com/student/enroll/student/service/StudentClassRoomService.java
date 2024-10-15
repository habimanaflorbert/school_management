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
        Student student=studentRepository.findById(request.getStudent().getId()).orElseThrow(() -> null);
        ClassRoom classRoom=classRoomRepository.findById(request.getClassRoom().getId()).orElseThrow(() -> null);
        request.setClassRoom(classRoom);
        request.setStudent(student);
      return studentClassRoomRepository.save(creaStudentClassRoom(request));
    }
    private StudentClassRoom creaStudentClassRoom(AddStudentClassRoomRequest request){
        return new StudentClassRoom(
            request.getStudent(),
            request.getClassRoom()
        );
    }
    
    
    @Override
    public StudentClassRoom updateStudentClassRoomById(AddStudentClassRoomRequest request, Long id) {
        Student student=studentRepository.findById(request.getStudent().getId()).orElseThrow(() -> null);
        ClassRoom classRoom=classRoomRepository.findById(request.getClassRoom().getId()).orElseThrow(() -> null);
        request.setClassRoom(classRoom);
        request.setStudent(student);
        return studentClassRoomRepository.findById(id)
        .map(existingStudentClassRoom -> updateExistingStudentClass(existingStudentClassRoom, request))
        .map(studentClassRoomRepository::save)
        .orElseThrow(()-> new ResourceNotFoundException("class room not found !"));
    }
    private StudentClassRoom updateExistingStudentClass(StudentClassRoom existingProduct,AddStudentClassRoomRequest request){
        existingProduct.setStudent(request.getStudent());
        existingProduct.setClassRoom(request.getClassRoom());
        return existingProduct;
        
    
    }

    @Override
    public void deleteStudentById(Long id) {
        studentClassRoomRepository.findById(id).ifPresentOrElse(studentClassRoomRepository::delete,()-> {new ResourceNotFoundException("Class Room not Found!");});

    }
    
}
