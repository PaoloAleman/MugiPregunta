package com.project.dominio.excepcion;

public class UsuarioExistenteException extends Throwable {
    public UsuarioExistenteException(String mensaje) {
        super(mensaje);
    }
}
