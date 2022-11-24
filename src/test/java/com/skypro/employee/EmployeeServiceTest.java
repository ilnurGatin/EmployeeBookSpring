package com.skypro.employee;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        this.employeeService = new EmployeeService();
        Stream.of(
                new EmployeeRequest("Ivan", "Ivanov", 1, 1000),
                new EmployeeRequest("Ivan", "Petrov", 1, 2000),
                new EmployeeRequest("Ivan", "Sidorov", 1, 3000),
                new EmployeeRequest("Sergfirst", "Ivanov", 2, 4000),
                new EmployeeRequest("Serglast", "Petrov", 2, 5000)
        ).forEach(employeeService::addEmployee);

    }

    @Test
    public void addEmployeeTest() {
        EmployeeRequest request = new EmployeeRequest("Test", "Test", 3, 3000);
        Employee result = employeeService.addEmployee(request);
        assertEquals(request.getFirstName(), result.getFirstName());
        assertEquals(request.getLastName(), result.getLastName());
        assertEquals(request.getSalary(), result.getSalary());
        assertEquals(request.getDepartment(), result.getDepartment());
        Assertions.assertThat(employeeService.getAllEmployees().contains(result));
    }

    @Test
    public void listOfAllEmployeesTest() {
        Collection<Employee> listEmployees = employeeService.getAllEmployees();
        Assertions.assertThat(listEmployees).hasSize(5);
        Assertions.assertThat(listEmployees).first().extracting(Employee::getFirstName).isIn("Ivan");
    }

    @Test
    public void sumOfSalary() {
        int sum = employeeService.getSalarySum();
        Assertions.assertThat(sum).isEqualTo(15000);
    }

    @Test
    public void getMinSalaryEmployeeTest() {
        Employee minSalaryEmployee = employeeService.getMinSalary();
        Assertions.assertThat(minSalaryEmployee).isNotNull();
        Assertions.assertThat(minSalaryEmployee).extracting(Employee::getSalary).isEqualTo(1000);
    }

    @Test
    public void getMaxSalaryEmployeeTest() {
        Employee maxSalaryEmployee = employeeService.getMaxSalary();
        Assertions.assertThat(maxSalaryEmployee).isNotNull();
        Assertions.assertThat(maxSalaryEmployee).extracting(Employee::getSalary).isEqualTo(5000);
    }

    @Test
    public void deleteEmployee() {
        employeeService.deleteEmployee(0);
        Collection<Employee> employeeAfterDelete = employeeService.getAllEmployees();
        Assertions.assertThat(employeeAfterDelete).hasSize(4);
        Assertions.assertThat(employeeAfterDelete).first().extracting(Employee::getLastName).isEqualTo("Petrov");
    }



    @Test
    public void getEmployeeWithSalaryHigherThanAverageTest() {
        Collection<Employee> employeesWithHighSalary = employeeService.getHighSalaryEmployees();
        Assertions.assertThat(employeesWithHighSalary).hasSize(2);
        Assertions.assertThat(employeesWithHighSalary)
                .first().extracting(Employee::getFirstName).isEqualTo("Sergfirst");
        Assertions.assertThat(employeesWithHighSalary)
                .last().extracting(Employee::getFirstName).isEqualTo("Serglast");
    }
}
