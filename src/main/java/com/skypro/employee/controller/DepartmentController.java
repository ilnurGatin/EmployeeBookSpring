package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllDepartmentEmployees(@RequestParam("id") int id) {
        return this.departmentService.getAllDepartmentEmployees(id);
    }

    @GetMapping("/employees/grouped")
    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment() {
        return this.departmentService.getEmployeesGroupedByDepartment();
    }

    @GetMapping("/{id}/salary/max")
    public int getMaxSalaryByDepartment(@PathParam("id") int id) {
        return this.departmentService.getMaxSalaryByDepartment(id);
    }

    @GetMapping("/{id}/salary/min")
    public int getMinSalaryByDepartment(@PathParam("id") int id) {
        return this.departmentService.getMinSalaryByDepartment(id);
    }
    @GetMapping("/{id}/salary/sum")
    public int getDepartmentSalary(@PathParam("id") int id) {
        return this.departmentService.getDepartmentSalary(id);
    }
}
