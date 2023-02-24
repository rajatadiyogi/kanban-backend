package com.vc.kanbanProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Project {

    @Id
    private int project_id;
    private String name;
    private String description;
    private Date duration;
    private List<Task> taskList;
    private List<String> assigned_emp;
    private String email; // to store email of person creating project



}
