package com.student.enroll.classRoom.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.student.enroll.classRoom.model.ClassRoom;

public interface ClassRoomRepository extends JpaRepository<ClassRoom,Long> {
  List<ClassRoom> findByRoomName(String roomName);  
}
