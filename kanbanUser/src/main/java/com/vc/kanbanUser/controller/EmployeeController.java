package com.vc.kanbanUser.controller;


import com.vc.kanbanUser.domain.Employee;
import com.vc.kanbanUser.exception.EmployeeAlreadyExists;
import com.vc.kanbanUser.exception.EmployeeNotFound;
import com.vc.kanbanUser.exception.ProjectAlreadyExists;
import com.vc.kanbanUser.service.KanbanService;
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
            employee.setTitle("FREE");
            return new ResponseEntity(kanbanService.saveEmployee(employee), HttpStatus.CREATED);
        }
        catch (EmployeeAlreadyExists e){
            throw new EmployeeAlreadyExists();
        }
    }

    @PostMapping("/employee/addProject/{project_id}")
    public ResponseEntity<?> addProjectId(@PathVariable int project_id,@RequestBody String email) throws ProjectAlreadyExists{

        System.out.println("kanban user controller : " + email);

        try{
            return new ResponseEntity<>(kanbanService.saveProjectIdInList(project_id, email),HttpStatus.OK);
        }catch (ProjectAlreadyExists e){
            throw  new ProjectAlreadyExists();
        }
    }



    @GetMapping("/employee/getAll")
    public ResponseEntity<?> getAllDetails(){
        List<Employee> users = kanbanService.getAllEmployees();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("employee/getProject/assigned/{email}")
    public ResponseEntity<List<Integer>> getAssignedProjects(@PathVariable String email){
       return new ResponseEntity<>(kanbanService.getAssignedProjects(email),HttpStatus.OK);
       // return kanbanService.getAssignedProjects(email);

    }



}
