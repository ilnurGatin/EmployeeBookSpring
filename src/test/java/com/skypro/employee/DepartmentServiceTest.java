package com.skypro.employee;

import com.skypro.employee.model.Employee;
import com.skypro.employee.service.DepartmentService;
import com.skypro.employee.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    private final List<Employee> EMPLOYEES = List.of(
            new Employee("Ivan", "Ivanov", 1, 1000),
            new Employee("Ivan", "Petrov", 1, 2000),
            new Employee("Ivan", "Sidorov", 1, 3000),
            new Employee("Sergfirst", "Ivanov", 2, 4000),
            new Employee("Serglast", "Petrov", 2, 5000)
    );
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        when(employeeService.getAllEmployees()).thenReturn(EMPLOYEES);
    }

    @Test
    void getEmployeesByDepartmentTest() {
        Collection<Employee> employeeCollection = departmentService.getAllDepartmentEmployees(1);
        Assertions.assertThat(employeeCollection).hasSize(3);
        Assertions.assertThat(employeeCollection).contains(EMPLOYEES.get(0), EMPLOYEES.get(1), EMPLOYEES.get(2));
    }

    @Test
    void getSumOfSalaryByDepartmentTest() {
        int sum = departmentService.getDepartmentSalary(2);
        Assertions.assertThat(sum).isEqualTo(9000);
    }

    @Test
    void getMaxSalaryInDepartmentTest() {
        int maxSalary = departmentService.getMaxSalaryByDepartment(1);
        Assertions.assertThat(maxSalary).isEqualTo(3000);
    }

    @Test
    void getMinSalaryInDepartmentTest() {
        int maxSalary = departmentService.getMinSalaryByDepartment(1);
        Assertions.assertThat(maxSalary).isEqualTo(1000);
    }

    @Test
    void getGroupedEmployeesByDepartmentTest() {
        Map<Integer, List<Employee>> groupedEmployees = departmentService.getEmployeesGroupedByDepartment();
        Assertions.assertThat(groupedEmployees).hasSize(2);
        Assertions.assertThat(groupedEmployees).containsKey(1).containsKey(2);
        Assertions.assertThat(groupedEmployees)
                .containsEntry(1, List.of(EMPLOYEES.get(0), EMPLOYEES.get(1), EMPLOYEES.get(2)));
        Assertions.assertThat(groupedEmployees)
                .containsEntry(2, List.of(EMPLOYEES.get(3), EMPLOYEES.get(4)));
    }

    @Test
    void whenNoEmplyeesThenReturnEmptyMap() {
        when(employeeService.getAllEmployees()).thenReturn(emptyList());
        Map<Integer, List<Employee>> groupedEmployees = departmentService.getEmployeesGroupedByDepartment();
        Assertions.assertThat(groupedEmployees).isEmpty();
    }

    @Test
    void whenNoEmplyeesThenReturnException() {
        when(employeeService.getAllEmployees()).thenReturn(emptyList());
        Assertions.assertThatThrownBy(() -> departmentService.getMaxSalaryByDepartment(1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
