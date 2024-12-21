package com.elias.service;

import com.elias.model.Employee;
import com.elias.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService{

    @Autowired
    private EmployeeRepository eRepository;
    @Override
    public List<Employee> getEmployees(Integer pageNumber, Integer pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
        return eRepository.findAll(pages).getContent();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return eRepository.save(employee);
    }

    @Override
    public Optional<Employee> getSingleEmployee(Long id) {
        return eRepository.findById(id);
    }

    @Override
    public void deleteEmployee(Long id) {
        eRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return eRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        return eRepository.findByName(name);
    }

    @Override
    public List<Employee> getEmployeebyNameAndLocation(String name, String location) {
        return eRepository.getEmployeesByNameAndLocaiton(name, location);
    }

    @Override
    public List<Employee> getEmployeeByKeyword(String keyword) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return eRepository.findByNameContaining(keyword, sort);
    }

    @Override
    public Integer deleteEmployeeById(Long id) {
        return eRepository.deleteEmployeeById(id);
    }
}
