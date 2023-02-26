package com.vc.UserAuthentication.service;

import com.vc.UserAuthentication.domain.Employee;
import com.vc.UserAuthentication.exception.EmployeeAlreadyExists;
import com.vc.UserAuthentication.exception.EmployeeNotFound;
import com.vc.UserAuthentication.exception.IncorrectPasswordException;
import com.vc.UserAuthentication.repository.EmployeeRepository;
import com.vc.UserAuthentication.security.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    @Autowired
    private Encryptor encryptor;

    @Value("${encryptor.alg}")
    private String alg;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee registerEmployee(Employee employee) throws EmployeeAlreadyExists, NoSuchAlgorithmException {
        if(employeeRepository.findById(employee.getEmail()).isPresent()){
           throw new  EmployeeAlreadyExists();
        }
        byte[] salt = encryptor.createdSalt();
        employee.setSalt(salt);
        String hash = encryptor.generateHash(employee.getPassword(), alg,salt);
        employee.setHash(hash);
        employee.setPassword(null);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findByEmailAndPassword(String email, String password) throws EmployeeNotFound, NoSuchAlgorithmException, IncorrectPasswordException {
        Employee employee;
        if(employeeRepository.findById(email).isEmpty()){
            throw new EmployeeNotFound();
        }else{
            employee = employeeRepository.findById(email).get();
            String reHash = encryptor.generateHash(password, alg,employee.getSalt());
            if(employee.getHash().equals(reHash)){
                employee.setSalt(null);
                employee.setHash(null);
                return employee;
            }else {
                throw new IncorrectPasswordException();
            }
        }
    }
}
