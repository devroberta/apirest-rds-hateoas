package com.devroberta.dioclass.apirest.controller;

import com.devroberta.dioclass.apirest.entity.Person;
import com.devroberta.dioclass.apirest.repository.PersonRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public String helloworld() {
        return ("This is my API Rest in Spring boot with AWS RDS!");
    }

    @GetMapping("/person")
    public List<Person> findAllPerson() {
        return personRepository.findAll();
    }

    @GetMapping("/person/{id}")
    public Person findById(@PathVariable Long id) {
        return personRepository.findById(id).get();
    }
}
