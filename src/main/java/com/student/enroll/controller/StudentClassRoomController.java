package com.student.enroll.controller;
import java.util.List;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.student.enroll.exception.ResourceNotFoundException;
import com.student.enroll.response.ApiResponse;
import com.student.enroll.student.model.StudentClassRoom;
import com.student.enroll.student.request.AddStudentClassRoomRequest;
import com.student.enroll.student.service.IStudentClassRoomService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/student-classroom")
public class StudentClassRoomController {

    private final IStudentClassRoomService studentClassRoomService;


    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllStudentClassRooom() {
        List<StudentClassRoom> students=studentClassRoomService.getAllStudentClassRoom();
        return ResponseEntity.ok(new ApiResponse(null, students));
    }

           
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addStudentClassRoom(@Valid @RequestBody AddStudentClassRoomRequest classRoom) {
        try {
            System.out.println("classRoom");
            StudentClassRoom theClassRoom=studentClassRoomService.addStudentClassRoom(classRoom);
            return ResponseEntity.ok(new ApiResponse("added sucessful", theClassRoom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }}
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateStduentClassRoom(@PathVariable Long id, @RequestBody AddStudentClassRoomRequest classRoomRequest) {
        try {
            StudentClassRoom theRoom=studentClassRoomService.updateStudentClassRoomById(classRoomRequest,id);
            return ResponseEntity.ok(new ApiResponse("updated success", theRoom));
        } catch (ResourceNotFoundException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<ApiResponse> deleteClassRoomById(@PathVariable Long roomId){
        try {
            studentClassRoomService.deleteStudentById(roomId);
            return ResponseEntity.ok(new ApiResponse("Deleted sccessfull", roomId));
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    
    }
    
}
