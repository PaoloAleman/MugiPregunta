package com.project.dominio.excepcion;

public class SexoInexistenteException extends Exception {
    public SexoInexistenteException(String mensaje) {
        super(mensaje);
    }
}
