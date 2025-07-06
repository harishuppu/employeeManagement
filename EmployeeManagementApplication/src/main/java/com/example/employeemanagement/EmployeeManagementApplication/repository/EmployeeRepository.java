package com.example.employeemanagement.EmployeeManagementApplication.repository;

import com.example.employeemanagement.EmployeeManagementApplication.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT DISTINCT e FROM Employee e " +
            "LEFT JOIN e.performanceReviews pr " +
            "LEFT JOIN e.employeeProjects ep " +
            "LEFT JOIN ep.project p " +
            "WHERE (:reviewDate IS NULL OR pr.reviewDate = :reviewDate) " +
            "AND (:minScore IS NULL OR pr.score >= :minScore) " +
            "AND (:maxScore IS NULL OR pr.score <= :maxScore) " +
            "AND (:departmentNames IS NULL OR e.department.name IN :departmentNames) " +
            "AND (:projectNames IS NULL OR p.name IN :projectNames)")
    List<Employee> findEmployeesWithFilters(
            @Param("reviewDate") LocalDate reviewDate,
            @Param("minScore") Double minScore,
            @Param("maxScore") Double maxScore,
            @Param("departmentNames") List<String> departmentNames,
            @Param("projectNames") List<String> projectNames
    );

    @Query("SELECT e FROM Employee e " +
            "LEFT JOIN FETCH e.department " +
            "LEFT JOIN FETCH e.manager " +
            "LEFT JOIN FETCH e.employeeProjects ep " +
            "LEFT JOIN FETCH ep.project " +
            "WHERE e.id = :id")
    Employee findEmployeeWithDetails(@Param("id") Long id);
}