package com.vc.UserAuthentication.service;

import com.vc.UserAuthentication.domain.Employee;
import com.vc.UserAuthentication.exception.EmployeeAlreadyExists;
import com.vc.UserAuthentication.exception.EmployeeNotFound;

public interface EmployeeService {

    Employee registerEmployee(Employee employee) throws EmployeeAlreadyExists;
    Employee findByEmailAndPassword(String email, String password) throws EmployeeNotFound;

}
