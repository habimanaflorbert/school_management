package com.student.enroll.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.enroll.student.model.StudentClassRoom;
import java.util.List;
import com.student.enroll.classRoom.model.ClassRoom;


public interface StudentClassRoomRepository  extends JpaRepository<StudentClassRoom, Long>{
   List<StudentClassRoom> findByClassRoom(ClassRoom classRoom);
   List<StudentClassRoom> findByStudent(ClassRoom classRoom);
   List<StudentClassRoom> findByStudentId(Long student);
   List<StudentClassRoom> findByClassRoomId(Long classRoom);
    
}
