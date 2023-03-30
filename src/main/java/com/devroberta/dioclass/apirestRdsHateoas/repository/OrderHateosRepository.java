package com.devroberta.dioclass.apirestRdsHateoas.repository;

import com.devroberta.dioclass.apirestRdsHateoas.entity.OrderHateoas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHateosRepository extends JpaRepository<OrderHateoas, Long> {

}
