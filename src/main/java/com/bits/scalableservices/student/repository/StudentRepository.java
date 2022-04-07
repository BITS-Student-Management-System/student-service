package com.bits.scalableservices.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bits.scalableservices.student.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	Student findByStudentId(Long studentId);

	Student findByDepartmentId(Long departmentId);
}
