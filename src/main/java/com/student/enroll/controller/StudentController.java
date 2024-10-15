package com.student.enroll.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.enroll.response.ApiResponse;
import com.student.enroll.student.model.Student;
import com.student.enroll.student.request.AddStudentRequest;
import com.student.enroll.student.request.UpdateStudentRequest;
import com.student.enroll.student.service.IStudentService;
import com.student.enroll.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/student")
public class StudentController {
    private final IStudentService studentService;
  
    
    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllStudent() {
        List<Student> students=studentService.getAllStudent();
        return ResponseEntity.ok(new ApiResponse(null, students));
    }
    
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addStudent(@RequestBody AddStudentRequest request){
        try{
        Student theStudent=studentService.addStudent(request);
        return ResponseEntity.ok(new ApiResponse("added", theStudent));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), request));
        }
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateStudentById(@PathVariable Long id, @RequestBody UpdateStudentRequest entity) {
        try{
        Student theStudent=studentService.updateStudentById(entity, id);
        return ResponseEntity.ok(new ApiResponse("updated", theStudent));
        }catch(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Long id){
    try{
        studentService.deleteStudentById(id);
        return ResponseEntity.ok(new ApiResponse("deleted", null));
    }catch (Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
    }
    }
    
    @GetMapping("/by-firstName")
      public ResponseEntity<ApiResponse> getSudentByFirstName(@RequestParam String firstName) {
        try {
            System.out.println("ffff");
            List<Student> students=studentService.getStudentByFirstName(firstName);
 
            if(students.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No student found", students));
            }
            return ResponseEntity.ok(new ApiResponse("success", students));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    
    @GetMapping("/by-lastName")
      public ResponseEntity<ApiResponse> getStudentByLastName(@RequestParam String lastName) {
        try {
            List<Student> students=studentService.getStudentByLastName(lastName);
 
            if(students.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No student found", students));
            }
            return ResponseEntity.ok(new ApiResponse("success", students));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/firstName-and-LastName")
    public ResponseEntity<ApiResponse> getStudentByFirstNameAndLast(@RequestParam String firstName,@RequestParam String lastName) {
        try {
            List<Student> students=studentService.getStudentByFirstNameAndLastName(firstName,lastName);

            if(students.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No product found", students));
            }
            return ResponseEntity.ok(new ApiResponse("success", students));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    
    
    
}
