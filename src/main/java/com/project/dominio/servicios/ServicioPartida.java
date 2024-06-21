package com.project.dominio.servicios;

import com.project.dominio.entidades.Partida;
import com.project.dominio.entidades.Pregunta;

public interface ServicioPartida {
    String definirSiLaRespuestaEsCorrecta(Integer idRespuesta, Pregunta pregunta);

    void actualizarPuntajePartida(Partida partida);

    void finalizarPartida(Partida partida);
}
