package com.student.enroll.student.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AddStudentClassRoomRequest {
    @NotNull(message = "ID is required")
    private Long classRoom;
    @NotNull(message = "ID is required")
    private Long student;    
}
