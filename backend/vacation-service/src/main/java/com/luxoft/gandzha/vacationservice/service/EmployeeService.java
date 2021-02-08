package com.luxoft.gandzha.vacationservice.service;

import com.luxoft.gandzha.vacationservice.model.Employee;
import com.luxoft.gandzha.vacationservice.model.VacationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    Employee findById(Long id);

    int getVacationBalance(Long id);

    Employee save(Long id, VacationRequest vacationRequest);

    List<VacationRequest> getLisOfVacationRequests(Long id);
}
