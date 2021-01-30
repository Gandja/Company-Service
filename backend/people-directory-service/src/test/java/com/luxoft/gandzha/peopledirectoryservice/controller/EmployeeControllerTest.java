package com.luxoft.gandzha.peopledirectoryservice.controller;

import com.luxoft.gandzha.peopledirectoryservice.model.Employee;
import com.luxoft.gandzha.peopledirectoryservice.service.EmployeeServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EmployeeServiceImpl employeeService;

    @Test
    @DisplayName("Check request for creation employee")
    void createTest() throws Exception {

        String json = "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Denis\",\n" +
                "        \"lastName\": \"Gandzha\",\n" +
                "        \"dateOfBirth\": \"1994-05-05\",\n" +
                "        \"position\": \"Programmer\",\n" +
                "        \"phoneNumber\": \"460-45-98\",\n" +
                "        \"email\": \"dg@mail.com\"\n" +
                "    }";

        RequestBuilder request = MockMvcRequestBuilders
                .post("/employee")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @DisplayName("Check request for deleting employee by id")
    void deleteTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .delete("/employee/{id}", 1);

        mockMvc.perform(request)
                .andExpect(status().isAccepted());
    }

    @Test
    @DisplayName("Check request for getting all employees")
    void findAllTest() throws Exception {

        Employee employee = new Employee(1l, "Denis", "Gandzha",
                LocalDate.of(1994, 05, 05), "Programmer",
                "460-45-98", "dg@mail.com");

        when(employeeService.findAll()).thenReturn(Arrays.asList(employee));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/employees")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"Denis\",\n" +
                        "        \"lastName\": \"Gandzha\",\n" +
                        "        \"dateOfBirth\": \"1994-05-05\",\n" +
                        "        \"position\": \"Programmer\",\n" +
                        "        \"phoneNumber\": \"460-45-98\",\n" +
                        "        \"email\": \"dg@mail.com\"\n" +
                        "    }]"))
                .andReturn();
    }

    @Test
    @DisplayName("Check request for finding employee by name")
    void findByName() throws Exception {

        Employee employee = new Employee(1l, "Denis", "Gandzha",
                LocalDate.of(1994, 05, 05), "Programmer",
                "460-45-98", "dg@mail.com");

        when(employeeService.findByName("Denis")).thenReturn(employee);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/employee")
                .param("name", "Denis")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "    {\n" +
                                "        \"id\": 1,\n" +
                                "        \"name\": \"Denis\",\n" +
                                "        \"lastName\": \"Gandzha\",\n" +
                                "        \"dateOfBirth\": \"1994-05-05\",\n" +
                                "        \"position\": \"Programmer\",\n" +
                                "        \"phoneNumber\": \"460-45-98\",\n" +
                                "        \"email\": \"dg@mail.com\"\n" +
                                "    }"))
                .andReturn();
    }

    @Test
    @DisplayName("Check request for finding employee by id")
    void findById() throws Exception {

        Employee employee = new Employee(1l, "Denis", "Gandzha",
                LocalDate.of(1994, 05, 05), "Programmer",
                "460-45-98", "dg@mail.com");

        when(employeeService.findById(1l)).thenReturn(employee);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/employee/{id}", 1)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "    {\n" +
                                "        \"id\": 1,\n" +
                                "        \"name\": \"Denis\",\n" +
                                "        \"lastName\": \"Gandzha\",\n" +
                                "        \"dateOfBirth\": \"1994-05-05\",\n" +
                                "        \"position\": \"Programmer\",\n" +
                                "        \"phoneNumber\": \"460-45-98\",\n" +
                                "        \"email\": \"dg@mail.com\"\n" +
                                "    }"))
                .andReturn();
    }

    @Test
    @DisplayName("Check request for finding all employees by name or last name")
    void findAllByNameAndLastName() throws Exception {

        Employee employee = new Employee(1l, "Denis", "Gandzha",
                LocalDate.of(1994, 05, 05), "Programmer",
                "460-45-98", "dg@mail.com");

        when(employeeService.findAllByNameAndLastName("Denis"))
                .thenReturn(Arrays.asList(employee));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/employees/all")
                .param("text", "Denis")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[\n" +
                                "    {\n" +
                                "        \"id\": 1,\n" +
                                "        \"name\": \"Denis\",\n" +
                                "        \"lastName\": \"Gandzha\",\n" +
                                "        \"dateOfBirth\": \"1994-05-05\",\n" +
                                "        \"position\": \"Programmer\",\n" +
                                "        \"phoneNumber\": \"460-45-98\",\n" +
                                "        \"email\": \"dg@mail.com\"\n" +
                                "    }]"))
                .andReturn();
    }
}
