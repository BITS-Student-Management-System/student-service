package com.bits.scalableservices.student.VO;

import java.time.LocalDate;
import java.util.Date;

import lombok.Data;

@Data
public class StudentRequest {

	private String firstName;
	private String lastName;
	private String emailAddress;
	private Long departmentId;
	private String gender;
	private Date admissionDate = new Date();
	private int currentSemester = 1;

}
