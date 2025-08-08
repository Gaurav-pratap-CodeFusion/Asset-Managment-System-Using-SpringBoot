package com.gpcodefusion.assetmanager.repository;

import com.gpcodefusion.assetmanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE " +
            "CAST(e.empId AS string) LIKE CONCAT('%', :search, '%') OR " +
            "LOWER(e.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(e.email) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "CAST(e.departmentId AS string) LIKE CONCAT('%', :search, '%')")
    List<Employee> findByNameContainingIgnoreCase(@Param("search") String search);

    @Query("SELECT MAX(CAST(SUBSTRING(e.empId, 4) AS long)) FROM Employee e WHERE e.empId LIKE 'EMP%'")
    Long findMaxEmployeeIdNumber();

}
