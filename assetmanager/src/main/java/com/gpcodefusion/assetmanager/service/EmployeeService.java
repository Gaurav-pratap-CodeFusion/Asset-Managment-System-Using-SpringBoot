package com.gpcodefusion.assetmanager.service;

import com.gpcodefusion.assetmanager.model.Employee;
import com.gpcodefusion.assetmanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> searchEmployees(String searchTerm) {
        return employeeRepository.findByNameContainingIgnoreCase(searchTerm);
    }

    @Transactional
    public Employee saveEmployee(Employee employee) {
        if (employee.getEmpId() == null || employee.getEmpId().isEmpty()) {
            employee.setEmpId(generateNextEmployeeId());
        }
        return employeeRepository.save(employee);
    }

    public String generateNextEmployeeId() {
        Long maxIdNumber = employeeRepository.findMaxEmployeeIdNumber();
        if (maxIdNumber == null) {
            maxIdNumber = 0L;
        }
        return String.format("EMP%03d", maxIdNumber + 1);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}