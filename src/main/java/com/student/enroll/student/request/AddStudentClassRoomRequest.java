package com.student.enroll.student.request;

import com.student.enroll.classRoom.model.ClassRoom;
import com.student.enroll.student.model.Student;

import lombok.Data;

@Data
public class AddStudentClassRoomRequest {
    private ClassRoom classRoom;
    private Student student;

    
}
