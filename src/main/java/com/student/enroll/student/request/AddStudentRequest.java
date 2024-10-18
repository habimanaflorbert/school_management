package com.student.enroll.student.request;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddStudentRequest {
    @NotNull(message = "firstName cannot be null")
    @NotBlank(message = "firstName cannot be null")
    private String firstName;
    @NotNull(message = "lastName cannot be null")
    @NotBlank(message = "lastName cannot be null")
    private String lastName;
    @Email(message = "should be email please")
    @NotBlank(message = "email cannot be empty")
    private String email;
    @Size(min = 10, max = 11, message = "Phone Number must 10 or 11")
    private String phone;
    @Past(message = "dateOfBirth must be in the past")
    private Date dateOfBirth;  
}
