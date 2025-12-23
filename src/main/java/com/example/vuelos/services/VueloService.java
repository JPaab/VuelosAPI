package com.example.vuelos.services;

import com.example.vuelos.exceptions.BadRequestException;
import com.example.vuelos.exceptions.ConflictException;
import com.example.vuelos.exceptions.NotFoundException;
import com.example.vuelos.models.Vuelo;
import com.example.vuelos.repositories.VueloRepository;
import com.example.vuelos.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

@Service
public class VueloService {

    private final VueloRepository repository;

    public VueloService(VueloRepository repository) {
        this.repository = repository;
    }

    public Vuelo crear(Vuelo vuelo) {
        validar(vuelo);

        if (repository.existsNombreVuelo(vuelo.getNombreVuelo(), null)) {
            throw new ConflictException("El vuelo ya existe (nombreVuelo repetido)");
        }
        return repository.save(vuelo);
    }

    public Stream<Vuelo> listarFiltrados(String empresa, String lugarLlegada, LocalDate fechaSalida) {
        Stream<Vuelo> s = repository.findAll().stream();

        if (empresa != null && !empresa.isBlank()) {
            String e = empresa.trim().toLowerCase(Locale.ROOT);
            s = s.filter(v -> v.getEmpresa() != null && v.getEmpresa().toLowerCase(Locale.ROOT).equals(e));
        }

        if (lugarLlegada != null && !lugarLlegada.isBlank()) {
            String ll = lugarLlegada.trim().toLowerCase(Locale.ROOT);
            s = s.filter(v -> v.getLugarLlegada() != null && v.getLugarLlegada().toLowerCase(Locale.ROOT).equals(ll));

        }

        if (fechaSalida != null) {
            s = s.filter(v -> fechaSalida.equals(v.getFechaSalida()));
        }

        return s;
    }

    public List<Vuelo> listar(String empresa,
                              String lugarLlegada,
                              LocalDate fechaSalida,
                              String ordenarPor,
                              String ordenar) {

        Comparator<Vuelo> comp = buildComparator(ordenarPor);

        String ord = DateUtils.normalizeOrden(ordenar);
        boolean desc = ord != null && ord.equalsIgnoreCase("DESC");
        if (desc) comp = comp.reversed();

        return listarFiltrados(empresa, lugarLlegada, fechaSalida)
                .sorted(comp)
                .toList();

    }

    public Vuelo actualizar(int id, Vuelo vuelo) {
        Vuelo vueloEncontrado = obtenerPorId(id);
        validar(vuelo);

        if (repository.existsNombreVuelo(vuelo.getNombreVuelo(), id)) {
            throw new ConflictException("nombreVuelo ya utilizado");
        }

        vueloEncontrado.setNombreVuelo(vuelo.getNombreVuelo());
        vueloEncontrado.setEmpresa(vuelo.getEmpresa());
        vueloEncontrado.setLugarSalida(vuelo.getLugarSalida());
        vueloEncontrado.setLugarLlegada(vuelo.getLugarLlegada());
        vueloEncontrado.setFechaSalida(vuelo.getFechaSalida());
        vueloEncontrado.setFechaLlegada(vuelo.getFechaLlegada());

        return vueloEncontrado;
    }

    public void eliminar(int id) {
        boolean eliminado = repository.delete(id);
        if (!eliminado) {
            throw new NotFoundException("Vuelo inexistente");
        }
    }

    public Vuelo obtenerPorId(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Vuelo no encontrado"));
    }

    private void validar(Vuelo vuelo) {
        if (vuelo == null) {
            throw new BadRequestException("Datos incorrectos");
        }
        if (vuelo.getNombreVuelo() == null || vuelo.getNombreVuelo().isBlank()
                || vuelo.getEmpresa() == null || vuelo.getEmpresa().isBlank()
                || vuelo.getLugarSalida() == null || vuelo.getLugarSalida().isBlank()
                || vuelo.getLugarLlegada() == null || vuelo.getLugarLlegada().isBlank()
                || vuelo.getFechaSalida() == null
                || vuelo.getFechaLlegada() == null) {
            throw new BadRequestException("Datos incorrectos");
        }
        DateUtils.validarRango(vuelo.getFechaSalida(), vuelo.getFechaLlegada());
    }

    private Comparator<Vuelo> buildComparator(String ordenarPor) {
        // Aqui hacemos caso a la consigna. Al listar los vuelos, estaran ordenados por fechaSalida.
        if (ordenarPor == null || ordenarPor.isBlank()) {
            return Comparator.comparing(Vuelo::getFechaSalida);
        }

        return switch (ordenarPor.trim()) {
            case "fechaSalida" -> Comparator.comparing(Vuelo::getFechaSalida);
            case "empresa" -> Comparator.comparing(Vuelo::getEmpresa, String.CASE_INSENSITIVE_ORDER);
            case "lugarLlegada" -> Comparator.comparing(Vuelo::getLugarLlegada, String.CASE_INSENSITIVE_ORDER);
            default -> throw new BadRequestException("ordenarPor inválido. Usa empresa, lugarLlegada o fechaSalida");
        };
    }
}
