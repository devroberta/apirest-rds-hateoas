package com.devroberta.dioclass.apirestRdsHateoas.config;

import com.devroberta.dioclass.apirestRdsHateoas.entity.Employee;
import com.devroberta.dioclass.apirestRdsHateoas.entity.OrderHateoas;
import com.devroberta.dioclass.apirestRdsHateoas.entity.Status;
import com.devroberta.dioclass.apirestRdsHateoas.repository.EmployeeRepository;
import com.devroberta.dioclass.apirestRdsHateoas.repository.OrderHateosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class LoadBD {

    public static final Logger log = LoggerFactory.getLogger(LoadBD.class);

    @Bean
    CommandLineRunner loaddata(EmployeeRepository employeeRepositoryHateoas, OrderHateosRepository orderHateosRepository) {
        return args -> {
            log.info("Log of save event: " + employeeRepositoryHateoas.save(new Employee("Maria Silva", "Chef",
                    "avenida silveira dutra 1002")));
            log.info("log of save event: " + employeeRepositoryHateoas.save(new Employee("John Dutra", "Mecanico",
                    "rua joao freire 231")));
            log.info("Log of save event: " + employeeRepositoryHateoas.save(new Employee("Bilbo Baggins", "thief",
                    "The shine")));
            orderHateosRepository.save(new OrderHateoas(Status.COMPLETED, "review"));
            orderHateosRepository.save(new OrderHateoas(Status.IN_PROGRESS, "travel"));
            orderHateosRepository.save(new OrderHateoas(Status.IN_PROGRESS, "sale"));
            orderHateosRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });
        };
    }

}
