package com.bits.scalableservices.student.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course implements Serializable{
    private Long courseId;
    private String courseName;
    private String courseCode;
    private int semester;
    private Long departmentId;
}
