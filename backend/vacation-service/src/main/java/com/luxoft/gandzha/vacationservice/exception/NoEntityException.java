package com.luxoft.gandzha.vacationservice.exception;

public class NoEntityException extends Exception{
    @Override
    public String getMessage() {
        return "Employee not found";
    }
}
