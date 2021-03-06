package com.bits.scalableservices.student.service;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Queue;

import com.bits.scalableservices.student.client.CourseClient;
import com.bits.scalableservices.student.client.DepartmentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bits.scalableservices.student.VO.Course;
import com.bits.scalableservices.student.VO.Department;
import com.bits.scalableservices.student.VO.ResponseTemplate;
import com.bits.scalableservices.student.VO.StudentRequest;
import com.bits.scalableservices.student.entity.Student;
import com.bits.scalableservices.student.repository.StudentRepository;
import com.github.dozermapper.core.Mapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	private Queue queue;

	@Autowired
	Mapper mapper;

	@Autowired
	DepartmentClient departmentClient;

	@Autowired
	CourseClient courseClient;

	public ResponseTemplate getStudentDetails(Long studentId) {
		log.info("Inside getStudentWithDepartment of StudentService");
		ResponseTemplate responseTemplate = new ResponseTemplate();
		Student student = studentRepository.findByStudentId(studentId);

		Department department =  departmentClient.getDepartmentDetails(student.getDepartmentId());
		List<Course> coursesList = courseClient.getCourseDetails(student.getDepartmentId());
		responseTemplate.setStudent(student);
		responseTemplate.setDepartment(department);
		responseTemplate.setCourses(coursesList);

		return responseTemplate;
	}

	public Student saveStudent(StudentRequest studentRequest) {
		log.info("Inside saveStudent of StudentService");
		Student student = studentRepository.save(mapper.map(studentRequest, Student.class));
		log.info("Sending JMS message to Department Service : "+ studentRequest.toString());
		pushMessageIntoQueueByActiveMQ(studentRequest.getDepartmentId());
		return student;
	}

	/*
	 * Adding JMS ActiveMQ Part, pushing message into Queue
	 */
	private void pushMessageIntoQueueByActiveMQ(Long departmentId) {
		jmsTemplate.convertAndSend(queue, String.valueOf(departmentId));
		log.info("Sent student department details to Department Service");

	}

	public Student findStudentById(Long studentId) {
		log.info("Inside findStudentById of StudentService");
		return studentRepository.findByStudentId(studentId);
	}


}
