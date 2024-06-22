package com.project.dominio.servicios;

import com.project.dominio.entidades.Partida;
import com.project.dominio.entidades.Pregunta;
import com.project.dominio.entidades.PyR;
import com.project.dominio.entidades.Usuario;
import com.project.infraestructura.RepositorioActualizar;
import com.project.infraestructura.RepositorioObtener;
import com.project.infraestructura.RepositorioPartida;
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
    private RepositorioActualizar repositorioActualizar;
    @Autowired
    public ServicioPartidaImpl(RepositorioPartida repositorioPartida, RepositorioObtener repositorioObtener, RepositorioActualizar repositorioActualizar) {
        this.repositorioPartida = repositorioPartida;
        this.repositorioObtener = repositorioObtener;
        this.repositorioActualizar = repositorioActualizar;
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

    @Override
    public void actualizarPuntajePartida(Partida partida) {
        partida.setPuntos(partida.getPuntos()+1);
        repositorioActualizar.actualizarPartida(partida);
    }

    @Override
    public void finalizarPartida(Partida partida) {
        partida.setActiva(false);
        repositorioActualizar.actualizarPartida(partida);
    }

    @Override
    public void actualizarPuntajeUsuario(Usuario usuario) {
        usuario.setPuntaje(usuario.getPuntaje()+1);
        repositorioActualizar.actualizarUsuario(usuario);
    }
}
