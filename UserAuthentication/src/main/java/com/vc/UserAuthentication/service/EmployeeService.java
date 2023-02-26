package com.vc.UserAuthentication.service;

import com.vc.UserAuthentication.domain.Employee;
import com.vc.UserAuthentication.exception.EmployeeAlreadyExists;
import com.vc.UserAuthentication.exception.EmployeeNotFound;
import com.vc.UserAuthentication.exception.IncorrectPasswordException;

import java.security.NoSuchAlgorithmException;

public interface EmployeeService {

    Employee registerEmployee(Employee employee) throws EmployeeAlreadyExists, NoSuchAlgorithmException;
    Employee findByEmailAndPassword(String email, String password) throws EmployeeNotFound, NoSuchAlgorithmException, IncorrectPasswordException;

}
