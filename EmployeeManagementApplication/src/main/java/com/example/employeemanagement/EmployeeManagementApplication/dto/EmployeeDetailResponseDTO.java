package com.example.employeemanagement.EmployeeManagementApplication.dto;

import lombok.Data;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class EmployeeDetailResponseDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfJoining;
    private BigDecimal salary;
    private DepartmentDTO department;
    private ManagerDTO manager;
    private List<ProjectDTO> projects;
    private List<PerformanceReviewDTO> lastThreeReviews;

    @Data
    @Builder
    public static class DepartmentDTO {
        private Long id;
        private String name;
        private BigDecimal budget;
    }

    @Data
    @Builder
    public static class ManagerDTO {
        private Long id;
        private String name;
        private String email;
    }

    @Data
    @Builder
    public static class ProjectDTO {
        private Long id;
        private String name;
        private LocalDate startDate;
        private LocalDate endDate;
        private String role;
        private LocalDate assignedDate;
    }
}