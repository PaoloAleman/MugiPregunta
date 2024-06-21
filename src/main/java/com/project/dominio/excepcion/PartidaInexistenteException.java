package com.project.dominio.excepcion;

public class PartidaInexistenteException extends Exception {
    public PartidaInexistenteException(String mensaje) {
        super(mensaje);
    }
}
