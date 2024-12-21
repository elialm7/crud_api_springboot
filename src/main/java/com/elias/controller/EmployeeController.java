package com.elias.controller;

import com.elias.model.Employee;
import com.elias.service.EmployeeService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService eService;
    @Value("${app.name}")
    private String appName;
    @Value("${app.version}")
    private String appVersion;



    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(
            @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("pageSize") Integer pageSize
    ){
        return new ResponseEntity<>(eService.getEmployees(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id){
        Optional<Employee> employee = eService.getSingleEmployee(id);
        if(!employee.isPresent()){
            System.out.println("Employee is not present for id: "+id);
            throw new RuntimeException("Employee with id: "+id+" is not present in the db");
        }
        return new ResponseEntity<>(employee.get(), HttpStatus.OK);
    }

    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam("id")Long id){
        eService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody() Employee json)  {
        return new ResponseEntity<>(eService.saveEmployee(json), HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        employee.setId(id);
       return new ResponseEntity<>(eService.updateEmployee(employee), HttpStatus.OK);
    }

    @GetMapping("/employees/filterbyName")
    public ResponseEntity<List<Employee>> getEmployeeByName(@RequestParam("name")String name){
        return new ResponseEntity<>(eService.getEmployeesByName(name), HttpStatus.OK);
    }

    @GetMapping("/employees/nameloc")
    public ResponseEntity<List<Employee>> getEmployeeByNameAndLocation(@RequestParam("name") String name, @RequestParam("location")String location){
        return new ResponseEntity<>(eService.getEmployeebyNameAndLocation(name, location), HttpStatus.OK);
    }

    @GetMapping("/employees/namekey")
    public ResponseEntity<List<Employee>> getEmployeeByNameKeyword(@RequestParam("namekeyword")String keyword){
        return new ResponseEntity<>(eService.getEmployeeByKeyword(keyword), HttpStatus.OK);
    }


    @DeleteMapping("/employees/djpa/{id}")
    public ResponseEntity<HttpStatus> deleteEmployeebyId(@PathVariable("id")Long id){
        eService.deleteEmployeeById(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
