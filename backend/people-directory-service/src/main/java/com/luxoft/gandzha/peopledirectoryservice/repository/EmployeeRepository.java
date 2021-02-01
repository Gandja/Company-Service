package com.luxoft.gandzha.peopledirectoryservice.repository;

import com.luxoft.gandzha.peopledirectoryservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByName(String name);

    @Override
    Optional<Employee> findById(Long id);

    List<Employee> findAllByName(String name);

    List<Employee> findAllByLastName(String lastName);

    Employee findByEmail(String email);

    Employee findByPhoneNumber(String phoneNumber);

    List<Employee> findAllByPosition(String position);

}
