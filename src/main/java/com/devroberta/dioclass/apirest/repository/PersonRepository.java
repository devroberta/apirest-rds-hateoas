package com.devroberta.dioclass.apirest.repository;

import com.devroberta.dioclass.apirest.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
