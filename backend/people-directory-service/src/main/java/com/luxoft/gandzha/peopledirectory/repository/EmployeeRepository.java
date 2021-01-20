package com.luxoft.gandzha.peopledirectory.repository;

import com.luxoft.gandzha.peopledirectory.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByName(String name);

    List<Employee> findAllByName(String name);

    List<Employee> findAllByLastName(String lastName);

    Employee findByEmail(String email);

    Employee findByPhoneNumber(String phoneNumber);

    List<Employee> findAllByPosition(String position);

}
