package com.example.vuelos.controllers;

import com.example.vuelos.dtos.VueloMapper;
import com.example.vuelos.dtos.VueloResponseDTO;
import com.example.vuelos.models.ApiResponse;
import com.example.vuelos.services.VueloService;
import com.example.vuelos.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vuelos")
public class VueloController {

    private final VueloService service;

    public VueloController(VueloService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<VueloResponseDTO>>> listar(
            @RequestParam(required = false) String empresa,
            @RequestParam(required = false) String lugarLlegada,
            @RequestParam(required = false) String fechaSalida,
            @RequestParam(required = false) String ordenarPor,
            @RequestParam(required = false) String ordenar
    ) {
        LocalDate fecha = DateUtils.parseLocalDateOrThrow(fechaSalida, "fechaSalida");

        List<VueloResponseDTO> data = service
                .listar(empresa, lugarLlegada, fecha, ordenarPor, ordenar)
                .stream()
                .map(VueloMapper::toDTO)
                .toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, "Listado de vuelos", data));
    }
}
