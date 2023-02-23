package com.vc.UserAuthentication.service;

import com.vc.UserAuthentication.domain.Employee;
import com.vc.UserAuthentication.exception.EmployeeAlreadyExists;
import com.vc.UserAuthentication.exception.EmployeeNotFound;
import com.vc.UserAuthentication.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee registerEmployee(Employee employee) throws EmployeeAlreadyExists {
        if(employeeRepository.findById(employee.getEmail()).isPresent()){
           throw new  EmployeeAlreadyExists();
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findByEmailAndPassword(String email, String password) throws EmployeeNotFound {
        Employee employee = employeeRepository.findByEmailAndPassword(email,password);
        if(employee == null){
            throw new EmployeeNotFound();
        }
        return employee;
    }
}
