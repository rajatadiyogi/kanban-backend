package com.vc.UserAuthentication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Employee {


    private int emp_id;
    @Id
    private String email;
    private String password;
    private  String role;

}
