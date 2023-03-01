package com.vc.UserAuthentication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.data.annotation.Transient;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Employee {
    private String emp_id;
    @Id
    private String email;
    @Transient
    private String password;
    private  String username;
    private byte[] salt;
    private String hash;
    private String title;
}
