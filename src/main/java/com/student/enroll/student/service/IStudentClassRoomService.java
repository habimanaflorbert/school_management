package com.student.enroll.student.service;

import java.util.List;
import com.student.enroll.student.model.StudentClassRoom;
import com.student.enroll.student.request.AddStudentClassRoomRequest;

public interface IStudentClassRoomService {
    List<StudentClassRoom> getAllStudentClassRoom();
    List<StudentClassRoom> getStudentClassRoomByStudent(Long student);
    List<StudentClassRoom> getClassRoom(Long classRoom);
    StudentClassRoom addStudentClassRoom(AddStudentClassRoomRequest request);
    StudentClassRoom updateStudentClassRoomById(AddStudentClassRoomRequest request,Long id);
    void deleteStudentById(Long id);
}
