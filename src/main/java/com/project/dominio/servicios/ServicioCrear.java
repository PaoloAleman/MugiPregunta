package com.project.dominio.servicios;

import com.project.presentacion.clasesAuxiliares.DatosRegistro;

import java.io.IOException;

public interface ServicioCrear {
    void crearUsuario(DatosRegistro datos) throws IOException;
}
