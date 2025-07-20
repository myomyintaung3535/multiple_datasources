package com.mm.example.dbOne.repository;

import com.mm.example.dbOne.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("dbOneEmployeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
