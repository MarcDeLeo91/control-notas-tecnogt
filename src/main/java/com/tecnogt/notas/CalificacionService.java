package com.tecnogt.notas;

public class CalificacionService {
    private final EstudianteRepository estudianteRepository;

    public CalificacionService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public double calcularNota(double zona, double examen) {
        if (zona < 0 || zona > 65) {
            throw new IllegalArgumentException("La zona debe estar entre 0 y 65.");
        }
        if (examen < 0 || examen > 35) {
            throw new IllegalArgumentException("El examen debe estar entre 0 y 35.");
        }
        return zona + examen;
    }

    public Estado obtenerEstado(double nota) {
        return nota >= 61 ? Estado.APROBADO : Estado.REPROBADO;
    }

    public Estado registrar(String carne, double zona, double examen) {
        if (!estudianteRepository.existe(carne)) {
            throw new EstudianteNoEncontradoException(carne);
        }

        double nota = calcularNota(zona, examen);
        estudianteRepository.guardarNota(carne, nota);
        return obtenerEstado(nota);
    }
}