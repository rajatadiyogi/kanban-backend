package com.vc.kanbanUser.service;

import com.vc.kanbanUser.domain.Employee;

import com.vc.kanbanUser.exception.EmployeeAlreadyExists;
import com.vc.kanbanUser.exception.EmployeeNotFound;
import com.vc.kanbanUser.exception.ProjectAlreadyExists;
import com.vc.kanbanUser.proxy.EmployeeProxy;
import com.vc.kanbanUser.repository.EmployeeRepository;

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
    public Employee saveProjectIdInList(int project_id, String email) throws ProjectAlreadyExists {
        System.out.println(email);
       Employee employee = employeeRepository.findByEmail(email);

        System.out.println(employee);
       if(employee.getProject_id_list()==null){
           employee.setProject_id_list(Arrays.asList(project_id));
       }else{
           List<Integer> projectList = employee.getProject_id_list();
           projectList.add(project_id);
           employee.setProject_id_list(projectList);
       }
       return  employeeRepository.save(employee);
    }

    @Override
    public Employee findByEmail(String email) throws EmployeeNotFound {
        if (employeeRepository.findById(email).isEmpty()) {
            throw new EmployeeNotFound();
        }
        return employeeRepository.findByEmail(email);
    }

}
