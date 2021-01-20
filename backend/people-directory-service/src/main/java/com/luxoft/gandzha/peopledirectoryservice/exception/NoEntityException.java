package com.luxoft.gandzha.peopledirectoryservice.exception;

public class NoEntityException extends Exception{
    @Override
    public String getMessage() {
        return "Employee not found";
    }
}
