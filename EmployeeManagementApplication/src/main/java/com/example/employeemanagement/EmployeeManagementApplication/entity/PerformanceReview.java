package com.example.employeemanagement.EmployeeManagementApplication.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "performance_reviews")
@Data
@EqualsAndHashCode(exclude = {"employee"})
@ToString(exclude = {"employee"})
public class PerformanceReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @NotNull(message = "Review date is required")
    @Column(name = "review_date", nullable = false)
    private LocalDate reviewDate;

    @NotNull(message = "Score is required")
    @DecimalMin(value = "0.0", message = "Score must be at least 0.0")
    @DecimalMax(value = "10.0", message = "Score must be at most 10.0")
    @Column(nullable = false, precision = 3)
    private Double score;

    @Column(name = "review_comments", columnDefinition = "TEXT")
    private String reviewComments;
}