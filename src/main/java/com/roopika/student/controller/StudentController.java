package com.roopika.student.controller;

import com.roopika.student.VO.ResponseTemplate;
import com.roopika.student.entity.Student;
import com.roopika.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/")
    public ResponseEntity<Object> saveStudent(@RequestBody Student student){
        log.info("Inside saveStudent of StudentController");
        try {
            return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/{id}")
//    public Student findStudentById(@PathVariable("id") Long studentId){
//        log.info("Inside findStudentById of StudentController");
//        return studentService.findStudentById(studentId);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentWithDepartment(@PathVariable("id") Long studentId){
        log.info("Inside getStudentWithDepartment of StudentController");
        try {
            return new ResponseEntity<>(studentService.getStudentWithDepartment(studentId), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
