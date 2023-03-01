package com.vc.kanbanUser.service;

import com.vc.kanbanUser.domain.Employee;
import com.vc.kanbanUser.exception.EmployeeAlreadyExists;
import com.vc.kanbanUser.exception.EmployeeNotFound;
import com.vc.kanbanUser.exception.ProjectAlreadyExists;

import java.util.List;

public interface KanbanService {

    Employee saveEmployee(Employee employee) throws EmployeeAlreadyExists;

    List<Employee> getAllEmployees();


    Employee findByEmail(String email) throws EmployeeNotFound;

    Employee saveProjectIdInList(int project_id,String email) throws ProjectAlreadyExists;

    List<Integer> getAssignedProjects(String email);
}
