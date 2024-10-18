package com.student.enroll.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.enroll.classRoom.model.ClassRoom;
import com.student.enroll.classRoom.request.AddClassRoomRequest;
import com.student.enroll.classRoom.service.IClassRoomService;
import com.student.enroll.exception.ResourceNotFoundException;
import com.student.enroll.response.ApiResponse;
import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/classRoom")

public class ClassRoomController {
    private final IClassRoomService classRoomService;


    @GetMapping("/{classRoomId}")
    public ResponseEntity<ApiResponse> getClassRoomById(@PathVariable Long classRoomId) {
       try {
        ClassRoom room=classRoomService.getClassRoomById(classRoomId);
           return ResponseEntity.ok(new ApiResponse("success", room));
    } catch (ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
    }
    }
    
     
    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllStudent() {
        List<ClassRoom> students=classRoomService.getAllClassRoom();
        return ResponseEntity.ok(new ApiResponse(null, students));
    }
    
       
       @PostMapping("/add")
    public ResponseEntity<ApiResponse> addClassRoom(@Valid @RequestBody AddClassRoomRequest classRoom) {
        try {
            ClassRoom theClassRoom=classRoomService.addClassRoom(classRoom);
            return ResponseEntity.ok(new ApiResponse("added sucessful", theClassRoom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    

      @GetMapping("/by-roomName")
    public ResponseEntity<ApiResponse> getCLassRoomByRoomName(@RequestParam String roomName) {
        try {
            List<ClassRoom> rooms=classRoomService.getClassRoomByRoomName(roomName);
            if(rooms.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No product found", rooms));
            }
            return ResponseEntity.ok(new ApiResponse("success", rooms));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<ApiResponse> deleteClassRoomById(@PathVariable Long roomId){
        try {
            classRoomService.deleteClassRoomById(roomId);
            return ResponseEntity.ok(new ApiResponse("Deleted sccessfull", roomId));
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    
    }
    
    @PutMapping("/{roomId}")
    public ResponseEntity<ApiResponse> putMethodName(@PathVariable Long roomId, @RequestBody AddClassRoomRequest classRoomRequest) {
        try {
            ClassRoom theRoom=classRoomService.updateClassRoomById(roomId,classRoomRequest);
            return ResponseEntity.ok(new ApiResponse("updated success", theRoom));
        } catch (ResourceNotFoundException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    
    }
    
}
