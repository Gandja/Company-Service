package com.luxoft.gandzha.peopledirectoryservice.controller;

import com.luxoft.gandzha.peopledirectoryservice.model.Employee;
import com.luxoft.gandzha.peopledirectoryservice.service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeServiceImpl service;

    public EmployeeController(EmployeeServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/employee")
    public String create(@RequestBody @Valid Employee employee) {
        service.create(employee);
        return "Employee was created";
    }

    @DeleteMapping("/employee/{id}")
    public String delete(@PathVariable @Valid
                                 Long id) {
        service.delete(id);
        return "Employee was deleted";
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return service.findAll();
    }

    @GetMapping("/employee")
    public Employee findByName(@RequestParam("name") @NotNull String name) {
        return service.findByName(name);
    }

    @GetMapping("/employee/{id}")
    public Employee findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/employees/all")
    public List<Employee> findAllByNameAndLastName(@RequestParam("text") @NotNull String text) {
        return service.findAllByNameAndLastName(text);
    }
}
