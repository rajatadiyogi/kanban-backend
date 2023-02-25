package com.vc.kanbanProject.service;

import com.vc.kanbanProject.domain.Project;
import com.vc.kanbanProject.domain.Task;
import com.vc.kanbanProject.domain.User;
import com.vc.kanbanProject.exception.EmployeeNotFound;
import com.vc.kanbanProject.exception.ProjectAlreadyExists;
import com.vc.kanbanProject.exception.ProjectNotFound;
import com.vc.kanbanProject.proxy.ProjectProxy;
import com.vc.kanbanProject.rebbitMQ.EmailDTO;
import com.vc.kanbanProject.rebbitMQ.EmailProducer;
import com.vc.kanbanProject.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class KanbanServiceImpl implements KanbanService{


    private ProjectProxy employeeProxy;
    private ProjectRepository projectRepository;
    private EmailProducer emailProducer;

    @Autowired //constructor Autowired
    public KanbanServiceImpl( ProjectProxy employeeProxy, ProjectRepository projectRepository, EmailProducer emailProducer) {
        this.projectRepository = projectRepository;
        this.employeeProxy = employeeProxy;
        this.emailProducer = emailProducer;
    }

    /*@Override
    public Employee findByEmail(String email) throws EmployeeNotFound {
        if (employeeRepository.findById(email).isEmpty()) {
            throw new EmployeeNotFound();
        }
        return employeeRepository.findByEmail(email);
    }*/

    @Override
    public Project findById(int project_id) {
        return projectRepository.findById(project_id);
    }

    @Override
    public Project createProject(Project project) throws ProjectAlreadyExists {
        Project savedProject = projectRepository.save(project);
        System.out.println(project.getProject_id());
        System.out.println(project.getEmail());
        employeeProxy.addProjectId(project.getProject_id(), project.getEmail());
        return  savedProject;
    }

    @Override
    public Project deleteTaskFromProject(int project_id, String email, String task) {
        Project project = projectRepository.findById(project_id);
        if(project.getEmail().equals(email)){
            List<Task> list = project.getTaskList();
            list.removeIf(x->x.getName().equals(task));
            project.setTaskList(list);
        }

        return projectRepository.save(project);
    }

    @Override
    public Project addTask(Task task, int project_id) throws ProjectNotFound {
        Project project =  projectRepository.findById(project_id);

        if(project.getTaskList() ==null){
            project.setTaskList(Arrays.asList(task));
        }else {
            List<Task> tasks = project.getTaskList();
            tasks.add(task);
            project.setTaskList(tasks);
        }
        return projectRepository.save(project);
    }

    @Override
    public Project updateTaskStatus(int project_id, Task task) throws ProjectNotFound {
        Project project = projectRepository.findById(project_id);
        if(project == null){
            throw  new ProjectNotFound();
        }
        List<Task> list = project.getTaskList();
        for(Task task1 : list){
            if(task1.getName().equals(task.getName()) ){
                task1.setStatus(task.getStatus());
            }
        }
        project.setTaskList(list);
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(String email, Project project) throws ProjectNotFound {
        return null;
    }

    @Override
    public Project assignMember(int project_id, User user) throws EmployeeNotFound, ProjectNotFound {
       Project project =  projectRepository.findById(project_id);
        System.out.println("kan project service");
        System.out.println(user);
        System.out.println(user.getEmail());
        if(project.getAssigned_emp() ==null){
            project.setAssigned_emp(Arrays.asList(user.getEmail()));
        }else {
            List<String> assigned_employees = project.getAssigned_emp();
            assigned_employees.add(user.getEmail());
            project.setAssigned_emp(assigned_employees);
        }
        projectAssignEmailBuilder(project,user);
         employeeProxy.addProjectId(project_id, user.getEmail());
        return projectRepository.save(project);
    }



    @Override
    public List<Project> findByEmail(String email) {

        return projectRepository.findByEmail(email);
    }
    private void projectAssignEmailBuilder(Project project, User user) {
        String[] username = user.getEmail().split("@");
        String[] projectLeader =project.getEmail().split("@");
        String subject = "New Project Assigned by"+projectLeader[0];
        String bodyOfMail = "Dear "+username[0]+"\n You have be assigned with a new Project by"
                +"\n Title : **"+project.getName()+"**"
                +"\n Description : "+project.getDescription();
        EmailDTO dto = new EmailDTO();
        dto.setEmail(user.getEmail());
        dto.setEmail_adm(project.getEmail());
        dto.setSubject(subject);
        dto.setMsgBody(bodyOfMail);
        emailProducer.ProjectAssignedEmail(dto);
    }
}
