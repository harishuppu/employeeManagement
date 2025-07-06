package com.example.employeemanagement.EmployeeManagementApplication.dto;

import lombok.Data;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class EmployeeListResponseDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfJoining;
    private BigDecimal salary;
    private String departmentName;
    private String managerName;
}