package com.bits.scalableservices.student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long studentId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String emailAddress;
    @Column(nullable = false)
    private Long departmentId;
    private String gender;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date admissionDate;
    @Column(columnDefinition = "integer default 1")
    private int currentSemester;
}
