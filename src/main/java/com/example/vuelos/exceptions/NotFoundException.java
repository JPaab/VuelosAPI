package com.example.vuelos.exceptions;

// Excepción custom 404 Not Found
// Por ejemplo al listar por ID inexistente, al intentar eliminar algo que no existe etc.

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
