package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.OptionalInt;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {
        return this.employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public Employee craateEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/employees/salary/sum")
    public int getSalarySum() {
        return employeeService.getSalarySum();
    }

    @GetMapping("/employee/salary/min")
    public OptionalInt getMinSalary() {
        return employeeService.getMinSalary();
    }

    @GetMapping("/employee/salary/max")
    public OptionalInt getMaxSalary() {
        return employeeService.getMaxSalary();
    }

    @GetMapping("/employee/high-salary")
    public List<Employee> getHighSalaryEmployees() {
        return employeeService.getHighSalaryEmployees();
    }
}
