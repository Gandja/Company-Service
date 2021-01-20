package com.luxoft.gandzha.vacationservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    String name;

    @Column(name = "lastName")
    String lastName;

    @Column(name = "email")
    String email;

}
