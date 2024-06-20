package com.project.dominio.excepcion;

public class UsuarioInexistenteException extends Exception{
    public UsuarioInexistenteException(String mensaje) {
        super(mensaje);
    }
}
