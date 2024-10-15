package com.student.enroll.classRoom.request;

import lombok.Data;

@Data
public class AddClassRoomRequest {
    private Long id;
    private String roomName;  
    private int limit;
}
