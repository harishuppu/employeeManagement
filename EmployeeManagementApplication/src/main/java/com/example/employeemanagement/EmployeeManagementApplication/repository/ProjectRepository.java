package com.example.employeemanagement.EmployeeManagementApplication.repository;


import com.example.employeemanagement.EmployeeManagementApplication.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}