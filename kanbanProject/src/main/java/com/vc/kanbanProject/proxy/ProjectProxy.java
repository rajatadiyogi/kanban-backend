package com.vc.kanbanProject.proxy;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name = "kanban-user-service",url = "localhost:8083")
public interface ProjectProxy {
    @PostMapping("/kanban/employee/addProject/{project_id}")
    public ResponseEntity<?> addProjectId(@PathVariable int project_id,  String email);
}
