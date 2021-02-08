package com.luxoft.gandzha.vacationservice.service;

import com.luxoft.gandzha.vacationservice.exception.NoEntityException;
import com.luxoft.gandzha.vacationservice.model.Employee;

public interface EmployeeService {

    Employee findById(Long id) throws NoEntityException;
}
