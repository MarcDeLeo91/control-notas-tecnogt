package com.tecnogt.notas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CalificacionServiceTest {
    @Mock
    private EstudianteRepository repositorio;

    @InjectMocks
    private CalificacionService servicio;

    @Test
    void registraNotaAprobadaParaEstudianteExistente() {
        when(repositorio.existe("2024001")).thenReturn(true);

        Estado estado = servicio.registrar("2024001", 50, 30);

        assertEquals(Estado.APROBADO, estado);
        verify(repositorio).guardarNota("2024001", 80.0);
    }

    @Test
    void noRegistraNotaSiEstudianteNoExiste() {
        when(repositorio.existe("2024002")).thenReturn(false);

        assertThrows(EstudianteNoEncontradoException.class,
            () -> servicio.registrar("2024002", 50, 30));

        verify(repositorio, never()).guardarNota(anyString(), anyDouble());
    }

    @Test
    void registraNotaReprobadaParaEstudianteExistente() {
        when(repositorio.existe("2024003")).thenReturn(true);

        Estado estado = servicio.registrar("2024003", 30, 10);

        assertEquals(Estado.REPROBADO, estado);
        verify(repositorio).guardarNota("2024003", 40.0);
    }
}