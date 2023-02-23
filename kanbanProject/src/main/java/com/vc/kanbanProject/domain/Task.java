package com.vc.kanbanProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {

    private int task_id;
    private String name;
    private String status;
    private String priority;
}
