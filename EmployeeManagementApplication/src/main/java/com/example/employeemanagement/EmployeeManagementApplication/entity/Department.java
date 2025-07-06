package com.example.employeemanagement.EmployeeManagementApplication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
@EqualsAndHashCode(exclude = {"employees", "projects"})
@ToString(exclude = {"employees", "projects"})
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Department name is required")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Budget is required")
    @Positive(message = "Budget must be positive")
    @Column(nullable = false, precision = 12)
    private BigDecimal budget;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Project> projects;
}