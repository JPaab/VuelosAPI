package com.example.vuelos.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


// Validaciones basicas para lo que acepta el POST/PUT.
// Basicamente no expone directamente el modelo y valida el body JSN antes de entrar a la logica "".

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

