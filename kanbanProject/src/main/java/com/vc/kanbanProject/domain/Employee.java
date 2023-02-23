package com.vc.kanbanProject.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Employee {

    @Id
    private String email;
    private String password;
    private int emp_id;
    private String role;
    List<Project> projectList;
}
