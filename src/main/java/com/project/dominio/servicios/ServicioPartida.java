package com.project.dominio.servicios;

import com.project.dominio.entidades.Partida;
import com.project.dominio.entidades.PartidaPregunta;
import com.project.dominio.entidades.Usuario;

public interface ServicioPartida {
    String definirSiLaRespuestaEsCorrecta(Integer idRespuesta, PartidaPregunta partidaPregunta);

    void actualizarPuntajePartida(Partida partida);

    void finalizarPartida(Partida partida);

    void actualizarPuntajeUsuario(Usuario usuario);

    void desactivarTodasLasPartidasDelUsuario(Integer id);
}
