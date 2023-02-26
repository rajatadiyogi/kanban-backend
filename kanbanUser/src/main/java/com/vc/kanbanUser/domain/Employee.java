package com.vc.kanbanUser.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EmbeddedId;
import javax.persistence.GeneratedValue;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Employee {

    @Id
    private String email;
    @Transient
    private String password;
    private String emp_id;
    private String role;
    List<Integer> project_id_list;

    public Employee(String email, String password, String emp_id, String role) {
        this.email = email;
        this.emp_id = emp_id;
        this.role = role;
        this.password = password;
    }
}
