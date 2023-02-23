package com.vc.kanbanProject.service;

import com.vc.kanbanProject.domain.Employee;
import com.vc.kanbanProject.domain.Project;
import com.vc.kanbanProject.exception.EmployeeAlreadyExists;
import com.vc.kanbanProject.exception.EmployeeNotFound;

import java.util.List;

public interface KanbanService {

    Employee saveEmployee(Employee employee) throws EmployeeAlreadyExists;

    List<Employee> getAllEmployees();
    Employee saveProjectToList(Project project, String email) throws EmployeeNotFound;

    Employee findByEmail(String email) throws EmployeeNotFound;
}