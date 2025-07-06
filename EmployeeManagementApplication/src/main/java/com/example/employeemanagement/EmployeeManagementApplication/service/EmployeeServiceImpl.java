package com.example.employeemanagement.EmployeeManagementApplication.service;

import com.example.employeemanagement.EmployeeManagementApplication.entity.Employee;
import com.example.employeemanagement.EmployeeManagementApplication.dto.EmployeeListResponseDTO;
import com.example.employeemanagement.EmployeeManagementApplication.dto.EmployeeDetailResponseDTO;
import com.example.employeemanagement.EmployeeManagementApplication.dto.PerformanceReviewDTO;
import com.example.employeemanagement.EmployeeManagementApplication.entity.PerformanceReview;
import com.example.employeemanagement.EmployeeManagementApplication.repository.EmployeeRepository;
import com.example.employeemanagement.EmployeeManagementApplication.repository.PerformanceReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final PerformanceReviewRepository performanceReviewRepository;

    @Override
    public List<EmployeeListResponseDTO> getEmployeesWithFilters(
            LocalDate reviewDate,
            Double minScore,
            Double maxScore,
            List<String> departmentNames,
            List<String> projectNames) {

        log.info("Fetching employees with filters - reviewDate: {}, minScore: {}, maxScore: {}, departments: {}, projects: {}",
                reviewDate, minScore, maxScore, departmentNames, projectNames);

        List<Employee> employees = employeeRepository.findEmployeesWithFilters(
                reviewDate, minScore, maxScore, departmentNames, projectNames);

        return employees.stream()
                .map(this::convertToListResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDetailResponseDTO getEmployeeDetails(Long id) {
        log.info("Fetching employee details for ID: {}", id);

        Employee employee = employeeRepository.findEmployeeWithDetails(id);
        if (employee == null) {
            throw new RuntimeException("Employee not found with ID: " + id);
        }

        List<PerformanceReview> lastThreeReviews = performanceReviewRepository
                .findTop3ByEmployeeIdOrderByReviewDateDesc(id);

        return convertToDetailResponseDTO(employee, lastThreeReviews);
    }

    private EmployeeListResponseDTO convertToListResponseDTO(Employee employee) {
        return EmployeeListResponseDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .dateOfJoining(employee.getDateOfJoining())
                .salary(employee.getSalary())
                .departmentName(employee.getDepartment().getName())
                .managerName(employee.getManager() != null ? employee.getManager().getName() : null)
                .build();
    }

    private EmployeeDetailResponseDTO convertToDetailResponseDTO(Employee employee, List<PerformanceReview> reviews) {
        return EmployeeDetailResponseDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .dateOfJoining(employee.getDateOfJoining())
                .salary(employee.getSalary())
                .department(EmployeeDetailResponseDTO.DepartmentDTO.builder()
                        .id(employee.getDepartment().getId())
                        .name(employee.getDepartment().getName())
                        .budget(employee.getDepartment().getBudget())
                        .build())
                .manager(employee.getManager() != null ?
                        EmployeeDetailResponseDTO.ManagerDTO.builder()
                                .id(employee.getManager().getId())
                                .name(employee.getManager().getName())
                                .email(employee.getManager().getEmail())
                                .build() : null)
                .projects(employee.getEmployeeProjects().stream()
                        .map(ep -> EmployeeDetailResponseDTO.ProjectDTO.builder()
                                .id(ep.getProject().getId())
                                .name(ep.getProject().getName())
                                .startDate(ep.getProject().getStartDate())
                                .endDate(ep.getProject().getEndDate())
                                .role(ep.getRole())
                                .assignedDate(ep.getAssignedDate())
                                .build())
                        .collect(Collectors.toList()))
                .lastThreeReviews(reviews.stream()
                        .limit(3)
                        .map(review -> PerformanceReviewDTO.builder()
                                .id(review.getId())
                                .reviewDate(review.getReviewDate())
                                .score(review.getScore())
                                .reviewComments(review.getReviewComments())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}