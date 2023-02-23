package com.vc.kanbanProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project {

    @Id
    private int project_id;
    private String name;
    private String description;
    private Date duration;
    private List<Task> taskList;

}
