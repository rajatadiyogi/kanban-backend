package com.vc.kanbanProject.service;

import com.vc.kanbanProject.domain.Project;
import com.vc.kanbanProject.domain.Task;
import com.vc.kanbanProject.domain.User;
import com.vc.kanbanProject.exception.EmployeeAlreadyExists;
import com.vc.kanbanProject.exception.EmployeeNotFound;
import com.vc.kanbanProject.exception.ProjectAlreadyExists;
import com.vc.kanbanProject.exception.ProjectNotFound;

import java.util.List;

public interface KanbanService {

  Project createProject(Project project) throws ProjectAlreadyExists;

  Project deleteTaskFromProject(int project_id,String email,String task);

  Project addTask(Task task,int project_id) throws ProjectNotFound;

  Project updateTaskStatus(int project_id, Task task) throws ProjectNotFound;

  Project updateProject(String email, Project project) throws ProjectNotFound;

    Project findById(int project_id);

    List<Project> findByEmail(String email);

    Project assignMember(int project_id, User user) throws EmployeeNotFound,ProjectNotFound;
}
