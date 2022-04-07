package com.bits.scalableservices.student.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.bits.scalableservices.student.entity.Student;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplate {

    private Student student;
    private Department department;
    private List<Course> courses;

}
