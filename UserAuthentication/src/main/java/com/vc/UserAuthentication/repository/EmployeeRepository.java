package com.vc.UserAuthentication.repository;

import com.vc.UserAuthentication.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String>{
    Employee findByEmailAndPassword(String email, String password);
    Employee findByEmail(String email);
}
