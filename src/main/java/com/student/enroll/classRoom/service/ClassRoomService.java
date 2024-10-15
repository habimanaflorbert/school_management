package com.student.enroll.classRoom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.enroll.classRoom.model.ClassRoom;
import com.student.enroll.classRoom.repository.ClassRoomRepository;
import com.student.enroll.classRoom.request.AddClassRoomRequest;
import com.student.enroll.exception.ResourceNotFoundException;
import com.student.enroll.student.model.Student;
import com.student.enroll.student.request.UpdateStudentRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassRoomService implements IClassRoomService {

    private final ClassRoomRepository classRoomRepository;

    @Override
    public ClassRoom addClassRoom(AddClassRoomRequest classRoom) {
        return classRoomRepository.save(createStudent(classRoom));
    }

      private ClassRoom createStudent(AddClassRoomRequest classRoom){
        return new ClassRoom(
            classRoom.getRoomName(),
            classRoom.getLimit()
           
        );
    }


    @Override
    public List<ClassRoom> getClassRoomByRoomName(String roomName) {
      return classRoomRepository.findByRoomName(roomName);
    }

    @Override
    public List<ClassRoom> getAllClassRoom() {
      return classRoomRepository.findAll();
    }
    
    @Override
    public ClassRoom updateClassRoomById(Long id, AddClassRoomRequest request) {
        return classRoomRepository.findById(id)
       .map(existStudent->updateExistingClassRoom(existStudent, request))
       .map(classRoomRepository::save)
       .orElseThrow(()->new ResourceNotFoundException("ClassRoom not found !"));
    }
     private ClassRoom updateExistingClassRoom(ClassRoom existClassRoom,AddClassRoomRequest request){
        existClassRoom.setRoomName(request.getRoomName());
        existClassRoom.setLimitation(request.getLimit());
      
        return existClassRoom;
        
    }
    
    @Override
    public void deleteClassRoomById(Long id) {
        classRoomRepository.findById(id).ifPresentOrElse(classRoomRepository:: delete,()->{new ResourceNotFoundException(" no classRoom with this Id");});
    }

    @Override
    public ClassRoom getClassRoomById(Long roomId) {
        return classRoomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
    
}
