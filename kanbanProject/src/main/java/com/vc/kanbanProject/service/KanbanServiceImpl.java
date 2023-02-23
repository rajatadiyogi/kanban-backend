package com.vc.kanbanProject.service;

import com.vc.kanbanProject.domain.Employee;
import com.vc.kanbanProject.domain.Project;
import com.vc.kanbanProject.exception.EmployeeAlreadyExists;
import com.vc.kanbanProject.exception.EmployeeNotFound;
import com.vc.kanbanProject.exception.ProjectAlreadyExists;
import com.vc.kanbanProject.proxy.EmployeeProxy;
import com.vc.kanbanProject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class KanbanServiceImpl implements KanbanService{
    private EmployeeRepository employeeRepository;


    private EmployeeProxy employeeProxy;

    @Autowired
    public KanbanServiceImpl(EmployeeRepository employeeRepository, EmployeeProxy employeeProxy) {
        this.employeeRepository = employeeRepository;

        this.employeeProxy = employeeProxy;
    }

    @Override
    public Employee saveEmployee(Employee employee) throws EmployeeAlreadyExists {
        if(employeeRepository.findById(employee.getEmail()).isPresent()){
            throw new EmployeeAlreadyExists();
        }
        Employee savedEmployee = employeeRepository.save(employee);
        if(!(savedEmployee.getEmail().isEmpty())){
            ResponseEntity r = employeeProxy.registerEmployee(employee);
            System.out.println(r.getBody());
        }

        return savedEmployee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveProjectToList(Project project, String email) throws EmployeeNotFound {
        if(employeeRepository.findById(email).isEmpty()){
            throw new EmployeeNotFound();
        }
        Employee employee = employeeRepository.findByEmail(email);
        if(employee.getProjectList()==null){
            employee.setProjectList(Arrays.asList(project));
        }else {
            List<Project> projects = employee.getProjectList();
            projects.add(project);
            employee.setProjectList(projects);
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findByEmail(String email) throws EmployeeNotFound {
        if (employeeRepository.findById(email).isEmpty()) {
            throw new EmployeeNotFound();
        }
        return employeeRepository.findByEmail(email);
    }
}
