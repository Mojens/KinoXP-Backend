package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

  boolean existsByUserName(String userName);

  Employee findByUserName(String userName);

}
