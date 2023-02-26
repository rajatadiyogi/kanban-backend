package com.vc.UserAuthentication.controller;

import com.vc.UserAuthentication.domain.Employee;
import com.vc.UserAuthentication.exception.EmployeeAlreadyExists;
import com.vc.UserAuthentication.exception.EmployeeNotFound;
import com.vc.UserAuthentication.exception.IncorrectPasswordException;
import com.vc.UserAuthentication.security.SecurityTokenGenerator;
import com.vc.UserAuthentication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.Map;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public EmployeeController(EmployeeService employeeService, SecurityTokenGenerator securityTokenGenerator) {
        this.employeeService = employeeService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody Employee employee) throws EmployeeAlreadyExists, NoSuchAlgorithmException {
        return new ResponseEntity<>(employeeService.registerEmployee(employee), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Employee employee) throws EmployeeNotFound, NoSuchAlgorithmException, IncorrectPasswordException {
        Employee employee1 =  employeeService.findByEmailAndPassword(employee.getEmail(),employee.getPassword());
        Map<String,String> token = securityTokenGenerator.createToken(employee1);
        return new ResponseEntity<>(token,HttpStatus.OK);
    }
}
