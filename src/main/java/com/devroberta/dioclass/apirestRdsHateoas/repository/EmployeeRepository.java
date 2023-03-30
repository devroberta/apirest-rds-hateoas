package com.devroberta.dioclass.apirestRdsHateoas.repository;

import com.devroberta.dioclass.apirestRdsHateoas.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
