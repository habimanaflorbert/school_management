package com.student.enroll.student.model;

import java.beans.Transient;

import com.student.enroll.classRoom.model.ClassRoom;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class StudentClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="student")
    private Student student;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="class_room")
    private ClassRoom classRoom;
    
    // @Transient

    
    public StudentClassRoom(Student student, ClassRoom classRoom) {
        this.student = student;
        this.classRoom = classRoom;
    }


    
}
