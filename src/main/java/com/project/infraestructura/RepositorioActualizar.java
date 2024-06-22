package com.project.infraestructura;

import com.project.dominio.entidades.Partida;
import com.project.dominio.entidades.Usuario;

public interface RepositorioActualizar {
    void actualizarPartida(Partida partida);

    void actualizarUsuario(Usuario usuario);
}
