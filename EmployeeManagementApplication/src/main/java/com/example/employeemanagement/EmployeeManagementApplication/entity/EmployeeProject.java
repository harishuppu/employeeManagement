package com.example.employeemanagement.EmployeeManagementApplication.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "employee_projects")
@Data
@EqualsAndHashCode(exclude = {"employee", "project"})
@ToString(exclude = {"employee", "project"})
public class EmployeeProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @NotNull(message = "Assigned date is required")
    @Column(name = "assigned_date", nullable = false)
    private LocalDate assignedDate;

    @NotBlank(message = "Role is required")
    @Column(nullable = false)
    private String role;
}