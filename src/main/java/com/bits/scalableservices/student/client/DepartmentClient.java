package com.bits.scalableservices.student.client;

import com.bits.scalableservices.student.VO.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "department-service")
public interface DepartmentClient {

    @GetMapping("/departments/{departmentId}")
    Department getDepartmentDetails(@PathVariable Long departmentId);

}
