package com.luxoft.gandzha.vacationservice.repository;

import com.luxoft.gandzha.vacationservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Override
    Optional<Employee> findById(Long id);
}
