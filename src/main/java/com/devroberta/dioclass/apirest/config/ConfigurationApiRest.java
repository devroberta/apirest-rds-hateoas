package com.devroberta.dioclass.apirest.config;

import com.devroberta.dioclass.apirest.entity.Person;
import com.devroberta.dioclass.apirest.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationApiRest {

    @Bean
    public CommandLineRunner commandLineRunner(PersonRepository personRepository) {
        return args -> {
            personRepository.save(new Person("Joao", "Silva"));
            personRepository.save(new Person("Maria", "Brasil"));
        };
    }
}
