package com.vc.kanbanUser.proxy;

import com.vc.kanbanUser.domain.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "user-authentication-service",url = "localhost:8081")
public interface EmployeeProxy {

    @PostMapping("/employee/register")
    public ResponseEntity<?> registerEmployee(Employee employee);
}
