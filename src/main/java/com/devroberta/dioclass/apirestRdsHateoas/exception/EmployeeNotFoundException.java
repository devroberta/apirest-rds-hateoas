package com.devroberta.dioclass.apirestRdsHateoas.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id) {
        super("Could not find the Employee id: " + id);
    }
}
