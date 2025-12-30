package com.example.vuelos.dtos;

import com.example.vuelos.models.Vuelo;

import java.time.temporal.ChronoUnit;

// Mapper para convertir entre el modelo interno y los DTOs de la API
// Basicamente evita exponer directamente el modelo de dominio en los endpoints

// duracionDias no se guarda en el modelo ni en el repositorio
// Es un dato calculado a partir de fechaSalida y fechaLlegada para el ResponseDTO

public class VueloMapper {

    // Aqui no se asigna el ID, porque eso lo gestiona el repositorio
    // Y las validaciones basicas se ejecutan en el Controller con el @Valid

    public static Vuelo toModel(VueloRequestDTO dto) {
        Vuelo v = new Vuelo();
        v.setNombreVuelo(dto.getNombreVuelo());
        v.setEmpresa(dto.getEmpresa());
        v.setLugarSalida(dto.getLugarSalida());
        v.setLugarLlegada(dto.getLugarLlegada());
        v.setFechaSalida(dto.getFechaSalida());
        v.setFechaLlegada(dto.getFechaLlegada());
        return v;
    }

    public static VueloResponseDTO toDTO(Vuelo vuelo) {
        // ChronoUnit.DAYS.between calcula la diferencia exacta de días que hay entre los LocalDate
        long duracion = ChronoUnit.DAYS.between(vuelo.getFechaSalida(), vuelo.getFechaLlegada());
        return new VueloResponseDTO(
                vuelo.getId(),
                vuelo.getNombreVuelo(),
                vuelo.getEmpresa(),
                vuelo.getLugarSalida(),
                vuelo.getLugarLlegada(),
                vuelo.getFechaSalida(),
                vuelo.getFechaLlegada(),
                duracion
        );
    }
}
