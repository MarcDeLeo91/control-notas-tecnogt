package com.tecnogt.notas;

public class EstudianteNoEncontradoException extends RuntimeException {
    public EstudianteNoEncontradoException(String carne) {
        super("No se encontró un estudiante con carné: " + carne);
    }
}