package com.bits.scalableservices.student.client;

import com.bits.scalableservices.student.VO.Course;
import com.bits.scalableservices.student.VO.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "course-service")
public interface CourseClient {

    @GetMapping("/courses/getCourseListByDepartmentId/{departmentId}")
    List<Course> getCourseDetails(@PathVariable Long departmentId);

}
