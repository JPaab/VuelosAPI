package com.example.vuelos.models;

import lombok.Getter;
import lombok.Setter;

// Basicamente es lo que envuelve las respuestas de la API para el PostMan.

@Getter
@Setter
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
