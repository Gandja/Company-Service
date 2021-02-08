package com.luxoft.gandzha.vacationservice.service;

import com.luxoft.gandzha.vacationservice.exception.NoEntityException;
import com.luxoft.gandzha.vacationservice.model.Employee;
import com.luxoft.gandzha.vacationservice.repository.EmployeeRepository;

public class EmployeeServiceImpl implements EmployeeService{

    EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee findById(Long id) throws NoEntityException {
        return repository.findById(id).orElseThrow(NoEntityException::new);
    }


}
