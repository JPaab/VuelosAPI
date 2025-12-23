package com.example.vuelos.exceptions;

// Excepcion custom para el 400 Bad Request
// datos invalidos, fechas incoherentes etc.

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
