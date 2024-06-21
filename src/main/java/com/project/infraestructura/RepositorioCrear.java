package com.project.infraestructura;

import com.project.dominio.entidades.Partida;
import com.project.dominio.entidades.PartidaPregunta;
import com.project.dominio.entidades.Usuario;

public interface RepositorioCrear {
    void crearUsuario(Usuario usuario);

    void crearPartida(Partida partida);

    void crearPartidaPregunta(PartidaPregunta partidaPregunta);
}
