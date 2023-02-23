package com.project.NotificationService.Subcriber;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ProjectDTO {

    private int project_id;
    private String name;
    private String description;
    private Date duration;
}
