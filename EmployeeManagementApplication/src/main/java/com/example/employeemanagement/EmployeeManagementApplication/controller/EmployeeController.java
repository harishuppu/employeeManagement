package com.example.employeemanagement.EmployeeManagementApplication.controller;

import com.example.employeemanagement.EmployeeManagementApplication.dto.EmployeeDetailResponseDTO;
import com.example.employeemanagement.EmployeeManagementApplication.dto.EmployeeListResponseDTO;
import com.example.employeemanagement.EmployeeManagementApplication.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeListResponseDTO>> getEmployees(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reviewDate,
            @RequestParam(required = false) Double minScore,
            @RequestParam(required = false) Double maxScore,
            @RequestParam(required = false) List<String> departments,
            @RequestParam(required = false) List<String> projects) {

        log.info("GET /api/employees - reviewDate: {}, minScore: {}, maxScore: {}, departments: {}, projects: {}",
                reviewDate, minScore, maxScore, departments, projects);

        List<EmployeeListResponseDTO> employees = employeeService.getEmployeesWithFilters(
                reviewDate, minScore, maxScore, departments, projects);

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDetailResponseDTO> getEmployeeDetails(@PathVariable Long id) {
        log.info("GET /api/employees/{}", id);

        EmployeeDetailResponseDTO employee = employeeService.getEmployeeDetails(id);
        return ResponseEntity.ok(employee);
    }
}