package com.example.employeemanagement.EmployeeManagementApplication.service;

import com.example.employeemanagement.EmployeeManagementApplication.dto.EmployeeDetailResponseDTO;
import com.example.employeemanagement.EmployeeManagementApplication.dto.EmployeeListResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

    List<EmployeeListResponseDTO> getEmployeesWithFilters(
            LocalDate reviewDate,
            Double minScore,
            Double maxScore,
            List<String> departmentNames,
            List<String> projectNames
    );

    EmployeeDetailResponseDTO getEmployeeDetails(Long id);
}