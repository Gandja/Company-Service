package com.luxoft.gandzha.peopledirectoryservice.service;

import com.luxoft.gandzha.peopledirectoryservice.model.Employee;

import java.util.List;

public interface EmployeeService {

    void create(Employee employee);

    void delete(Long id);

    List<Employee> findAll();

    Employee findByName(String name);

    Employee findById(Long id);

    List<Employee> findAllByNameAndLastName(String text);

    List<Employee> findAllByLastName(String lastName);

    List<Employee> findAllByName(String name);

    Employee findByEmail(String email);

    Employee findByPhoneNumber(String phoneNumber);

    List<Employee> findAllByPosition(String position);
}


