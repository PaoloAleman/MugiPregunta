package com.project.dominio.servicios;

import com.project.dominio.entidades.Partida;
import com.project.dominio.entidades.PartidaPregunta;
import com.project.presentacion.clasesAuxiliares.DatosRegistro;

import java.io.IOException;

public interface ServicioCrear {
    void crearUsuario(DatosRegistro datos) throws IOException;

    void crearPartida(Partida partida);

    void crearPartidaPregunta(PartidaPregunta partidaPregunta);
}
