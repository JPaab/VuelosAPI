package com.example.vuelos.exceptions;

// Excepcion custom para el 409 Conflict.
// Por ejemplo los datos duplicados (nombreVuelo repetidos)

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
