package com.tecnogt.notas;

public interface EstudianteRepository {
    boolean existe(String carne);

    void guardarNota(String carne, double nota);
}