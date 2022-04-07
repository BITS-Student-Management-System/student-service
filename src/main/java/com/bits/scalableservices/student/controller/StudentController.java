package com.bits.scalableservices.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bits.scalableservices.student.VO.StudentRequest;
import com.bits.scalableservices.student.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping("/create")
	public ResponseEntity<Object> saveStudent(@RequestBody StudentRequest studentRequest) {
		log.info("Inside saveStudent of StudentController");
		try {
			return new ResponseEntity<>(studentService.saveStudent(studentRequest), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

//    @GetMapping("/{id}")
//    public Student findStudentById(@PathVariable("id") Long studentId){
//        log.info("Inside findStudentById of StudentController");
//        return studentService.findStudentById(studentId);
//    }

	public ResponseEntity<Object> getStudentWithDepartment(@PathVariable("id") Long studentId) {
		log.info("Inside getStudentWithDepartment of StudentController");
		try {
			return new ResponseEntity<>(studentService.getStudentWithDepartment(studentId), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
}
