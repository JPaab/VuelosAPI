package com.example.vuelos.utils;

import com.example.vuelos.exceptions.BadRequestException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE;

    private static LocalDate parseLocalDateOrThrow(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            return null;
        } try {
            return LocalDate.parse(value.trim(), ISO);
        } catch (DateTimeParseException e) {
            throw new BadRequestException("Formato inválido para " + fieldName + ". Usa yyyy-MM-dd.");
        }
    }

    public static void validarRango (LocalDate salida, LocalDate llegada) {
        if (salida != null && llegada != null && salida.isAfter(llegada)) {
            throw new BadRequestException("fechaSalida no puede ser posterior a fechaLlegada");
        }
    }

    public static String normalizeOrden(String ordenar) {
        if (ordenar == null) {
            return null;
        }

        String o = ordenar.trim();
        if (o.startsWith("\"") && o.endsWith("\"") && o.length() >= 2) {
            o = o.substring(1, o.length() - 1);
        }
        return o.trim();
    }
}
