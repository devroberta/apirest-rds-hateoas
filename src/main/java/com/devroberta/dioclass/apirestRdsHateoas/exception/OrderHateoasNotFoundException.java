package com.devroberta.dioclass.apirestRdsHateoas.exception;

public class OrderHateoasNotFoundException extends RuntimeException {

    public OrderHateoasNotFoundException(Long id) {
        super("Could not find the Order id: " + id);
    }
}
