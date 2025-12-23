package com.example.vuelos.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// Es lo que devuelve la API.
// Añadimos el bonus de duracionDias calculado.

@Getter
@Setter
@AllArgsConstructor
public class VueloResponseDTO {
    private int id;
    private String nombreVuelo;
    private String empresa;
    private String lugarSalida;
    private String lugarLlegada;
    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;
    private Long duracionDias;
}
