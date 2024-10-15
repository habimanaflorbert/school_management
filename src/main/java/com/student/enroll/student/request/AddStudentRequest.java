package com.student.enroll.student.request;

import java.sql.Date;

import lombok.Data;

@Data
public class AddStudentRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date dateOfBirth;  
}
