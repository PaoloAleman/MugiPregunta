package com.project.dominio.excepcion;

public class PasswordsDiferentesException extends Exception {
    public PasswordsDiferentesException(String mensaje) {
        super(mensaje);
    }
}
