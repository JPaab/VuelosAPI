package com.example.vuelos.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


// DTO de entrada para crear y actualizar vuelos (POST y PUT)
// Define los datos obligatorios que acepta la API en el body JSON
// Aplica validaciones basicas antes de que la petición entre a la logica

@Getter
@Setter
public class VueloRequestDTO {

    @NotBlank(message = "nombreVuelo obligatorio")
    private String nombreVuelo;

    @NotBlank(message = "empresa obligatorio")
    private String empresa;

    @NotBlank(message = "lugarSalida obligatorio")
    private String lugarSalida;

    @NotBlank(message = "lugarLlegada obligatorio")
    private String lugarLlegada;

    @NotNull(message = "fechaSalida obligatorio")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaSalida;

    @NotNull(message = "fechaLlegada obligatorio")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaLlegada;
}

