package com.devroberta.dioclass.apirestRdsHateoas.controller;


import com.devroberta.dioclass.apirestRdsHateoas.entity.Employee;
import com.devroberta.dioclass.apirestRdsHateoas.exception.EmployeeNotFoundException;
import com.devroberta.dioclass.apirestRdsHateoas.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;


    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PostMapping
    public Employee create(@RequestBody Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeRepository.findById(id).map(oldEmployee -> {
            oldEmployee.setName(employee.getName());
            oldEmployee.setAddress(employee.getAddress());
            oldEmployee.setRole(employee.getRole());
            return employeeRepository.save(oldEmployee);
        }).orElseGet(() -> {
            Employee newEmployee = new Employee(employee);
            return employeeRepository.save(newEmployee);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.deleteById(id);
    }
}
