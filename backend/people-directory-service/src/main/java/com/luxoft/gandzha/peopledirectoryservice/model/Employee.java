package com.luxoft.gandzha.peopledirectoryservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @NotEmpty
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "The date of birth should be correct")
    private Date dateOfBirth;

    @Column(name = "position")
    private String position;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    @Email(message = "Email should be valid")
    private String email;

}