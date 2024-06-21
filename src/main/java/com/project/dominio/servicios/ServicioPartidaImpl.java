package com.project.dominio.servicios;

import com.project.dominio.entidades.Pregunta;
import com.project.dominio.entidades.PyR;
import com.project.dominio.entidades.Respuesta;
import com.project.infraestructura.RepositorioObtener;
import com.project.infraestructura.RepositorioPartida;
import com.project.infraestructura.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("servicioPartida")
@Transactional
public class ServicioPartidaImpl implements ServicioPartida {
    private RepositorioPartida repositorioPartida;
    private RepositorioObtener repositorioObtener;
    @Autowired
    public ServicioPartidaImpl(RepositorioPartida repositorioPartida,RepositorioObtener repositorioObtener) {
        this.repositorioPartida = repositorioPartida;
        this.repositorioObtener = repositorioObtener;
    }

    @Override
    public String definirSiLaRespuestaEsCorrecta(Integer idRespuesta, Pregunta pregunta) {
        List<PyR> pyrs=repositorioObtener.obtenerRespuestasDePregunta(pregunta.getId()).stream().
                filter(pyr -> pyr.getRespuesta().getId()==idRespuesta && pyr.getEsCorrecta()==true).
                collect(Collectors.toList());
        String vista="redirect:/respuestaCorrecta";
        if (pyrs.isEmpty()) {
            vista="redirect:/respuestaIncorrecta";
        }
        return vista;
    }
}
