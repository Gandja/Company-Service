package com.luxoft.gandzha.vacationservice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "dateOfStart")
    private LocalDate dateOfStart;

    @Column(name = "vacation_balance")
    private int vacationBalance;

    @Column(name = "vacation_request")
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<VacationRequest> vacationRequest;

}
