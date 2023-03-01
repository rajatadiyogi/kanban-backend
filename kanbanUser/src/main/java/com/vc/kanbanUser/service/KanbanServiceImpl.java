package com.vc.kanbanUser.service;

import com.vc.kanbanUser.domain.Employee;

import com.vc.kanbanUser.exception.EmployeeAlreadyExists;
import com.vc.kanbanUser.exception.EmployeeNotFound;
import com.vc.kanbanUser.exception.ProjectAlreadyExists;
import com.vc.kanbanUser.proxy.EmployeeProxy;
import com.vc.kanbanUser.rabbitmq.EmailDTO;
import com.vc.kanbanUser.rabbitmq.EmailProducer;
import com.vc.kanbanUser.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class KanbanServiceImpl implements KanbanService{
    private EmployeeRepository employeeRepository;
    private EmployeeProxy employeeProxy;
    private EmailProducer emailProducer;
    private SequenceGenerator generator;

    @Value("${mg.sequenceName}")
    private String seq_name;
    @Autowired
    public KanbanServiceImpl(EmployeeRepository employeeRepository, EmployeeProxy employeeProxy,EmailProducer emailProducer,
                             SequenceGenerator generator) {
        this.employeeRepository = employeeRepository;
        this.employeeProxy = employeeProxy;
        this.emailProducer = emailProducer;
        this.generator = generator;
    }

    @Override
    public Employee saveEmployee(Employee employee) throws EmployeeAlreadyExists {
        if(employeeRepository.findById(employee.getEmail()).isPresent()){
            throw new EmployeeAlreadyExists();
        }
        int num = 1000+generator.getSequenceNumber(seq_name);
        employee.setEmp_id("EMP"+num);
        System.out.println(employee);
        ResponseEntity<?> r = employeeProxy.registerEmployee(employee);
        Employee savedEmployee = employeeRepository.save(employee);
        EmailDTO dto = new EmailDTO(employee.getEmail(),"Thank you for reg withUs","Welcome to focus");
        emailProducer.welcomeEmail(dto);

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

    @Override
    public List<Integer> getAssignedProjects(String email) {
        Employee employee = employeeRepository.findByEmail(email);
        return employee.getProject_id_list();
    }
}
