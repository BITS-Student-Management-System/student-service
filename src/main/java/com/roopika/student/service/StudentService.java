package com.roopika.student.service;

import com.roopika.student.VO.Course;
import com.roopika.student.VO.CourseList;
import com.roopika.student.VO.Department;
import com.roopika.student.VO.ResponseTemplate;
import com.roopika.student.entity.Student;
import com.roopika.student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RestTemplate restTemplate;

    public Student saveStudent(Student student) {
        log.info("Inside saveStudent of StudentService");
        return studentRepository.save(student);
    }

    public Student findStudentById(Long studentId) {
        log.info("Inside findStudentById of StudentService");
        return studentRepository.findByStudentId(studentId);
    }

    public ResponseTemplate getStudentWithDepartment(Long studentId) {
        log.info("Inside getStudentWithDepartment of StudentService");
        ResponseTemplate responseTemplate = new ResponseTemplate();
        Student student = studentRepository.findByStudentId(studentId);

        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + student.getDepartmentId(),
                Department.class);

//        CourseList courseList =
//                restTemplate.getForObject(
//                        "http://COURSE-SERVICE/courses/" + student.getCurrentSemester(),
//                        CourseList.class
//                );
        List<Course> coursesList = new ArrayList<>();
                //courseList.getCourses();

        responseTemplate.setStudent(student);
        responseTemplate.setDepartment(department);
        responseTemplate.setCourses(coursesList);

        return responseTemplate;
    }
}
