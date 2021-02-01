package com.luxoft.gandzha.peopledirectoryservice.service;

import com.luxoft.gandzha.peopledirectoryservice.exception.NoEntityException;
import com.luxoft.gandzha.peopledirectoryservice.model.Employee;
import com.luxoft.gandzha.peopledirectoryservice.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeServiceImplTest {

    @Mock
    EmployeeRepository repository;

    @InjectMocks
    EmployeeServiceImpl service;

    @Test
    void saveTest() {
        Employee employee = createEmployee();

        when(repository.save(any(Employee.class))).thenReturn(new Employee());

        Employee createdEmployee = service.save(employee);

        assertEquals(employee.getName(), createdEmployee.getName());
    }

    @Test
    void deleteTest() {
        Employee employee = createEmployee();

        doNothing().when(repository).delete(employee);

        service.delete(employee.getId());

        verify(repository, times(1)).getOne(employee.getId());
    }

    @Test
    void findAllTest() {
        when(repository.findAll()).thenReturn(Arrays.asList(createEmployee()));

        List<Employee> created = service.findAll();

        assertEquals(1, created.size());
    }

    @Test
    void findByNameTest() {
        Employee employee = createEmployee();

        when(repository.findByName(anyString())).thenReturn(employee);

        Employee found = service.findByName("Denis");

        assertEquals(employee.getName(), found.getName());
    }

    @Test
    void findByIdTest() throws NoEntityException {
        Employee employee = createEmployee();

        when(repository.findById(anyLong())).thenReturn(Optional.of(employee));

        service.findById(employee.getId());

        verify(repository, times(1)).findById(employee.getId());
    }

    @Test()
    void findByIdExceptionTest() {
        Employee employee = createEmployee();
        assertThrows(NoEntityException.class, () -> service.findById(employee.getId()));
    }

    @Test
    void findByNameAndLastNameTest() {
        List<Employee> employees = Arrays.asList(createEmployee());
        String name = employees.get(0).getName();

        when(repository.findAllByName(anyString())).thenReturn(employees);
        when(repository.findAllByLastName(anyString())).thenReturn(employees);

        service.findAllByNameAndLastName(name);

        verify(repository, times(1)).findAllByName(name);
        verify(repository, times(1)).findAllByLastName(name);
    }

    @Test
    void findAllByLastName() {
        List<Employee> employees = Arrays.asList(createEmployee());
        String name = employees.get(0).getName();

        when(repository.findAllByLastName(anyString())).thenReturn(employees);

        service.findAllByLastName(name);

        verify(repository, times(1)).findAllByLastName(name);
    }

    @Test
    void findAllByName() {
        List<Employee> employees = Arrays.asList(createEmployee());
        String name = employees.get(0).getName();

        when(repository.findAllByName(anyString())).thenReturn(employees);

        service.findAllByName(name);

        verify(repository, times(1)).findAllByName(name);
    }

    @Test
    void findByEmail() {
        Employee employee = createEmployee();

        when(repository.findByEmail(anyString())).thenReturn(employee);

        service.findByEmail(employee.getEmail());

        verify(repository, times(1)).findByEmail(employee.getEmail());
    }

    @Test
    void findByPhoneNumber() {
        Employee employee = createEmployee();

        when(repository.findByPhoneNumber(anyString())).thenReturn(employee);

        service.findByPhoneNumber(employee.getPhoneNumber());

        verify(repository, times(1)).findByPhoneNumber(employee.getPhoneNumber());
    }

    @Test
    void findAllByPosition() {
        List<Employee> employees = Arrays.asList(createEmployee());
        String position = employees.get(0).getPosition();

        when(repository.findAllByPosition(anyString())).thenReturn(employees);

        service.findAllByPosition(position);

        verify(repository, times(1)).findAllByPosition(position);
    }

    private Employee createEmployee() {
        Employee employee = new Employee(1l, "Denis", "Gandzha",
                LocalDate.parse("1994-05-05"), "Programmer",
                "460-45-98", "dg@mail.com");

        return employee;
    }
}
