package com.example.employeemanagement.EmployeeManagementApplication.repository;


import com.example.employeemanagement.EmployeeManagementApplication.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> { }

