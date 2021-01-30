package com.luxoft.gandzha.vacationservice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vacation_request")
@Data
public class VacationRequest {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "vacationType")
    private String vacationType;

    @Column(name = "employee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;
}
