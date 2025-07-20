package com.mm.example.dbTwo.repository;


import com.mm.example.dbTwo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("dbTwoEmployeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
