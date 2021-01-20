package com.luxoft.gandzha.peopledirectory.controller;

import com.luxoft.gandzha.peopledirectory.model.Employee;
import com.luxoft.gandzha.peopledirectory.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping("/employee")
    public void create(@RequestBody Employee employee) {
        service.save(employee);
    }

    @DeleteMapping("/employee")
    public void delete(@RequestBody Employee employee) {
        service.delete(employee);
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        List<Employee> employees = service.findAll();
        return employees;
    }

    @GetMapping("/employee")
    public Employee findByName(@RequestParam("name") String name) {
        Employee employee = service.findByName(name);
        return employee;
    }

    @GetMapping("/employees/all")
    public List<Employee> findAllByNameAndLastName(@RequestParam("text") String text) {
        List<Employee> employees = new ArrayList<>();
        employees.addAll(service.findAllByName(text));
        employees.addAll(service.findAllByLastName(text));
        return employees;
    }
}
