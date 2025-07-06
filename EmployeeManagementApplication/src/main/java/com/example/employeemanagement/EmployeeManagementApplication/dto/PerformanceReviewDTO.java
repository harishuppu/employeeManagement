package com.example.employeemanagement.EmployeeManagementApplication.dto;


import lombok.Data;
import lombok.Builder;

import java.time.LocalDate;

@Data
@Builder
public class PerformanceReviewDTO {
    private Long id;
    private LocalDate reviewDate;
    private Double score;
    private String reviewComments;
}