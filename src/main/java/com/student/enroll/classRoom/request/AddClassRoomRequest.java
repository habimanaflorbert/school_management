package com.student.enroll.classRoom.request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.NotBlank;

@Data
public class AddClassRoomRequest {
    
    
    @NotNull(message = "roomName cannot be null")
    @NotBlank(message = "roomName cannot be null")
    private String roomName;  
    
    @NotNull(message = "limit cannot be null")
    @Min(value = 10, message = "Limit must be at least 10")
    @Max(value = 50, message = "Limit cannot be more than 50")
    private Integer limit;  // Use Integer instead of int
}