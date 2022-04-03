package com.roopika.student.VO;

import com.roopika.student.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplate {

    private Student student;
    private Department department;

}
