package com.student.enroll.classRoom.service;

import java.util.List;


import com.student.enroll.classRoom.model.ClassRoom;
import com.student.enroll.classRoom.request.AddClassRoomRequest;


public interface IClassRoomService {
    ClassRoom addClassRoom(AddClassRoomRequest classRoom);
    List<ClassRoom> getClassRoomByRoomName(String roomName);
    List<ClassRoom> getAllClassRoom();
    ClassRoom updateClassRoomById(Long id, AddClassRoomRequest request);
    void deleteClassRoomById(Long id);
    ClassRoom getClassRoomById(Long roomId);
}
