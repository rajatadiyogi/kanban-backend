package com.vc.kanbanProject.controller;


import com.vc.kanbanProject.domain.Project;
import com.vc.kanbanProject.domain.Task;
import com.vc.kanbanProject.domain.User;
import com.vc.kanbanProject.exception.EmployeeAlreadyExists;
import com.vc.kanbanProject.exception.EmployeeNotFound;
import com.vc.kanbanProject.exception.ProjectAlreadyExists;
import com.vc.kanbanProject.exception.ProjectNotFound;
import com.vc.kanbanProject.service.KanbanService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/kan")
public class EmployeeController {

    private KanbanService kanbanService;

    private ResponseEntity<?> responseEntity;
    String email;

    @Autowired
    public EmployeeController(KanbanService kanbanService) {
        this.kanbanService = kanbanService;
    }

    @PostMapping("/project/newProject")
    public ResponseEntity<?> createProject(@RequestBody Project project, HttpServletRequest request) throws ProjectAlreadyExists {
        ResponseEntity responseEntity;
        try {
            /*System.out.println(request);
            Claims claims = (Claims) request.getAttribute("claims");
            String email = claims.getSubject();
            System.out.println("clok : "+ claims);
            System.out.println(email);*/

            responseEntity = new ResponseEntity(kanbanService.createProject(project), HttpStatus.OK);

        }catch (ProjectAlreadyExists e)
        {
            throw new ProjectAlreadyExists();
        }
        return responseEntity;
    }

    @PostMapping("/project/assign/{project_id}")
    public ResponseEntity<?> assignMember(@RequestBody User user, @PathVariable int project_id) throws
            EmployeeNotFound, ProjectNotFound{
        ResponseEntity responseEntity;
        System.out.println("Insside kan project controller : "+email);
        try{
            responseEntity = new ResponseEntity(kanbanService.assignMember(project_id,user),HttpStatus.OK);

        }catch (ProjectNotFound e){
            throw  new ProjectNotFound();
        }
        return  responseEntity;
    }

    @PostMapping("/project/addTask/{project_id}")
    public ResponseEntity<?> addTask(@RequestBody Task task, @PathVariable int project_id) throws ProjectNotFound{
        ResponseEntity responseEntity;
        try{
            responseEntity = new ResponseEntity(kanbanService.addTask(task,project_id),HttpStatus.OK);

        }catch (ProjectNotFound e){
            throw  new ProjectNotFound();
        }
        return  responseEntity;
    }
    @GetMapping("/project/getProjects")
    public ResponseEntity<?> getProjectDetails(HttpServletRequest request) {


            Claims claims = (Claims) request.getAttribute("claims");
            email = claims.getSubject();
            System.out.println("user email from claims :: " + claims.getSubject());
            System.out.println("email " + email);
            System.out.println("email in user " + email);

            responseEntity = new ResponseEntity<>(kanbanService.findByEmail(email), HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("project/getProject/{project_id}")
    public ResponseEntity<?> getProjectWithId(@PathVariable int project_id){
        return  new ResponseEntity<>(kanbanService.findById(project_id),HttpStatus.OK);
    }

    @DeleteMapping("project/delete/{project_id}")
    public ResponseEntity<?> deleteTask(@PathVariable int project_id, @RequestBody Task task,HttpServletRequest request){

        Claims claims = (Claims) request.getAttribute("claims");
        String email = claims.getSubject();
        System.out.println("user email from claims :: " + claims.getSubject());
        System.out.println("email " + email);


            return new ResponseEntity<>(kanbanService.deleteTaskFromProject(project_id,email,task.getName()),HttpStatus.OK);


    }

    @PutMapping("project/updateTask/{project_id}")
    public ResponseEntity<?> updateTaskStatus(@PathVariable int project_id, @RequestBody Task task) throws ProjectNotFound{
        ResponseEntity responseEntity1;

        try {
            responseEntity1 =  new ResponseEntity<>(kanbanService.updateTaskStatus(project_id,task),HttpStatus.OK);
        }catch (ProjectNotFound e){
            throw  new ProjectNotFound();
        }
        return responseEntity1;

    }




}
