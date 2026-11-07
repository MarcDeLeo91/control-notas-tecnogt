package com.tecnogt.notas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EstadoNotaTest {
    private final CalificacionService servicio = new CalificacionService(null);

    @ParameterizedTest
    @CsvSource({
        "0, REPROBADO",
        "60.99, REPROBADO",
        "61, APROBADO",
        "100, APROBADO"
    })
    void determinaEstado(double nota, String estadoEsperado) {
        assertEquals(Estado.valueOf(estadoEsperado), servicio.obtenerEstado(nota));
    }
}