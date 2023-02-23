package com.vc.kanbanProject.controller;


import com.vc.kanbanProject.domain.Employee;
import com.vc.kanbanProject.domain.Project;
import com.vc.kanbanProject.exception.EmployeeAlreadyExists;
import com.vc.kanbanProject.exception.EmployeeNotFound;
import com.vc.kanbanProject.service.KanbanService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/kanban")
public class EmployeeController {

    private KanbanService kanbanService;

    private ResponseEntity<?> responseEntity;
    String email;

    @Autowired
    public EmployeeController(KanbanService kanbanService) {
        this.kanbanService = kanbanService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) throws EmployeeAlreadyExists {
        try {
            return new ResponseEntity(kanbanService.saveEmployee(employee), HttpStatus.CREATED);
        }
        catch (EmployeeAlreadyExists e){
            throw new EmployeeAlreadyExists();
        }
    }

    @PostMapping("/employee/addProject")
    public ResponseEntity<?> saveUserProductToList(@RequestBody Project project, HttpServletRequest request) throws EmployeeNotFound {
        ResponseEntity responseEntity;
        try {
            System.out.println(request);
            Claims claims = (Claims) request.getAttribute("claims");
            String email = claims.getSubject();
            System.out.println(claims);
            System.out.println(email);

            responseEntity = new ResponseEntity(kanbanService.saveProjectToList(project, email), HttpStatus.OK);

        }catch (EmployeeNotFound e)
        {
            throw new EmployeeNotFound();
        }
        return responseEntity;
    }

    @GetMapping("/employee/getProjects")
    public ResponseEntity<?> getProjectDetails(HttpServletRequest request) throws EmployeeNotFound {

        try{
            Claims claims = (Claims) request.getAttribute("claims");
            email = claims.getSubject();
            System.out.println("user email from claims :: " + claims.getSubject());
            System.out.println("email " + email);
            System.out.println("email in user " + email);
            Employee employee = kanbanService.findByEmail(email);
            List<Project> list = employee.getProjectList();
            responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
        }catch(EmployeeNotFound e)
        {
            throw new EmployeeNotFound();
        }
        return responseEntity;
    }

    @GetMapping("employee/getAll")
    public ResponseEntity getAllDetails(){
        List<Employee> users = kanbanService.getAllEmployees();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}
