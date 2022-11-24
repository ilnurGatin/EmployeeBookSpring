package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Stream<Employee> getDepartmentStream(int departmentId) {
        return employeeService.getAllEmployees()
                .stream()
                .filter(e -> e.getDepartment() == departmentId);
    }

    public Collection<Employee> getAllDepartmentEmployees(int departmentId) {
        return getDepartmentStream(departmentId)
                .collect(Collectors.toList());
    }

    public int getDepartmentSalary(int departmentId) {
        return getDepartmentStream(departmentId)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public int getMaxSalaryByDepartment(int departmentId) {
        return getDepartmentStream(departmentId)
                .mapToInt(Employee::getSalary)
                .max()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int getMinSalaryByDepartment(int departmentId) {
        return getDepartmentStream(departmentId)
                .mapToInt(Employee::getSalary)
                .min()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment() {
        return employeeService.getAllEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
