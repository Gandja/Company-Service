package com.luxoft.gandzha.vacationservice.service;

import com.luxoft.gandzha.vacationservice.exception.NoEntityException;
import com.luxoft.gandzha.vacationservice.model.Employee;
import com.luxoft.gandzha.vacationservice.model.VacationRequest;
import com.luxoft.gandzha.vacationservice.repository.EmployeeRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee findById(Long id) {
        Employee employee = null;
        try {
            employee = repository.findById(id).orElseThrow(NoEntityException::new);
        } catch (NoEntityException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return employee;
    }

    @Override
    public int getVacationBalance(Long id) {
        Employee employee;
        try {
            employee = repository.findById(id).orElseThrow(NoEntityException::new);
        } catch (NoEntityException e) {
            e.printStackTrace();
            e.getMessage();
            return -1;
        }
        return employee.getVacationBalance();
    }

    @Override
    public Employee save(Long id, VacationRequest vacationRequest) {
        Employee employee = null;
        try {
            employee = repository.findById(id).orElseThrow(NoEntityException::new);
            employee.getVacationRequest().add(vacationRequest);
        } catch (NoEntityException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return employee;
    }

    @Override
    public List<VacationRequest> getLisOfVacationRequests(Long id) {
        List<VacationRequest> requestList = null;
        Employee employee;
        try {
             employee = repository.findById(id).orElseThrow(NoEntityException::new);
             requestList = employee.getVacationRequest();
        } catch (NoEntityException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return requestList;
    }

}
