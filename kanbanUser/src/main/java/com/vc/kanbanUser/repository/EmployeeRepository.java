package com.vc.kanbanUser.repository;

import com.vc.kanbanUser.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Employee findByEmail(String email);
}
