package com.luxoft.gandzha.peopledirectoryservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.luxoft.gandzha.peopledirectoryservice.model.Employee;
import com.luxoft.gandzha.peopledirectoryservice.service.EmployeeServiceImpl;
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
    EmployeeServiceImpl service;

    @Test
    void createEmployeeTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/employee")
                .content(asJsonString(createEmployee()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void deleteTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .delete("/employee/{id}", 1);

        mockMvc.perform(request)
                .andExpect(status().isAccepted());
    }

    @Test
    void findAllTest() throws Exception {
        Employee employee = createEmployee();

        when(service.findAll()).thenReturn(Arrays.asList(employee));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/employees")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[" + asJsonString(employee) +"]"))
                .andReturn();
    }

    @Test
    void findByNameTest() throws Exception {
        Employee employee = createEmployee();

        when(service.findByName("Denis")).thenReturn(employee);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/employee")
                .param("name", "Denis")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(employee)))
                .andReturn();
    }

    @Test
    void findByIdTest() throws Exception {
        Employee employee = createEmployee();

        when(service.findById(1l)).thenReturn(employee);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/employee/{id}", 1)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(employee)))
                .andReturn();
    }

    @Test
    void findAllByNameAndLastNameTest() throws Exception {
        Employee employee = createEmployee();

        when(service.findAllByNameAndLastName("Denis"))
                .thenReturn(Arrays.asList(employee));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/employees/all")
                .param("text", "Denis")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[" + asJsonString(employee) +"]"))
                .andReturn();
    }

    private Employee createEmployee() {
        Employee employee = new Employee(1l, "Denis", "Gandzha",
                LocalDate.parse("1994-05-05"), "Programmer",
                "460-45-98", "dg@mail.com");

        return employee;
    }

    private String asJsonString(Employee employee) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = "";
        try {
            json = mapper.writeValueAsString(employee);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
