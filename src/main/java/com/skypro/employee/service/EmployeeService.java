package com.skypro.employee.service;

import com.skypro.employee.exception.InvalidEmployeeNameCredentialsException;
import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final Map<Integer, Employee> employees = new HashMap<>();

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Employee should be set");
        }
        if (!StringUtils.isAlpha(employeeRequest.getFirstName()) || !StringUtils.isAlpha(employeeRequest.getLastName())) {
            throw new InvalidEmployeeNameCredentialsException();
        }
        Employee employee = new Employee(
                    StringUtils.capitalize(employeeRequest.getFirstName()),
                    StringUtils.capitalize(employeeRequest.getLastName()),
                    employeeRequest.getDepartment(),
                    employeeRequest.getSalary());


        employees.put(employee.getId(), employee);
        return employee;
    }

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }


    public int getSalarySum() {
        return employees.values().
                stream().
                mapToInt(Employee::getSalary).
                sum();
    }

    public Employee getMinSalary() {
        return employees.values().
                stream().
                min(Comparator.comparing(Employee::getSalary)).
                get();
    }

    public Employee getMaxSalary() {
        return employees.values().
                stream().
                max(Comparator.comparing(Employee::getSalary)).
                get();
    }

    public List<Employee> getHighSalaryEmployees() {
        int averageSalary = getSalarySum() / employees.size();
        return employees.values().stream().filter(e -> e.getSalary() > averageSalary).collect(Collectors.toList());
    }

    public Employee deleteEmployee(Integer employeeId) {
        return employees.remove(employeeId);
    }
}
