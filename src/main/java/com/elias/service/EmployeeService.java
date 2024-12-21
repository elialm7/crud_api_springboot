package com.elias.service;

import com.elias.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getEmployees(Integer pageNumber, Integer pagesize);
    Employee saveEmployee(Employee employee);
    Optional<Employee> getSingleEmployee(Long id);
    void deleteEmployee(Long id);
    Employee updateEmployee(Employee employee);
    List<Employee> getEmployeesByName(String name);
    List<Employee> getEmployeebyNameAndLocation(String name, String location);
    List<Employee> getEmployeeByKeyword(String keyword);
    Integer deleteEmployeeById(Long id);

}
