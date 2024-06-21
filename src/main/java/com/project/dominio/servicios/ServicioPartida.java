package com.project.dominio.servicios;

import com.project.dominio.entidades.Pregunta;

public interface ServicioPartida {
    String definirSiLaRespuestaEsCorrecta(Integer idRespuesta, Pregunta pregunta);
}
