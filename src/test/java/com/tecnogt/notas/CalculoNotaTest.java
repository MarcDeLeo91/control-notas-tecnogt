package com.tecnogt.notas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CalculoNotaTest {
    private final CalificacionService servicio = new CalificacionService(null);

    @Test
    void calculaNotaNormal() {
        assertEquals(80.0, servicio.calcularNota(50, 30), 0.001);
    }

    @Test
    void calculaNotaMinima() {
        assertEquals(0.0, servicio.calcularNota(0, 0), 0.001);
    }

    @Test
    void calculaNotaMaxima() {
        assertEquals(100.0, servicio.calcularNota(65, 35), 0.001);
    }

    @Test
    void rechazaZonaMayorAlMaximo() {
        assertThrows(IllegalArgumentException.class, () -> servicio.calcularNota(70, 30));
    }

    @Test
    void rechazaExamenNegativo() {
        assertThrows(IllegalArgumentException.class, () -> servicio.calcularNota(50, -1));
    }

    @Test
    void rechazaZonaNegativa() {
        assertThrows(IllegalArgumentException.class, () -> servicio.calcularNota(-1, 30));
    }

    @Test
    void rechazaZonaMayorAlLimite() {
        assertThrows(IllegalArgumentException.class, () -> servicio.calcularNota(65.01, 30));
    }

    @Test
    void rechazaExamenMayorAlLimite() {
        assertThrows(IllegalArgumentException.class, () -> servicio.calcularNota(50, 35.01));
    }
}