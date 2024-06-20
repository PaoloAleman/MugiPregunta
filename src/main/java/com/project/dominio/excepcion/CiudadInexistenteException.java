package com.project.dominio.excepcion;

public class CiudadInexistenteException extends Exception {
    public CiudadInexistenteException(String mensaje) {
        super(mensaje);
    }
}
