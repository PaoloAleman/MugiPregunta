package com.project.dominio.servicios;

import com.project.dominio.entidades.*;
import com.project.infraestructura.RepositorioActualizar;
import com.project.infraestructura.RepositorioObtener;
import com.project.infraestructura.RepositorioPartida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
    public String definirSiLaRespuestaEsCorrecta(Integer idRespuesta, PartidaPregunta partidaPregunta) {
        List<PyR> pyrs = repositorioObtener.obtenerRespuestasDePregunta(partidaPregunta.getPregunta().getId()).stream()
                .filter(pyr -> Objects.equals(pyr.getRespuesta().getId(), idRespuesta) && pyr.getEsCorrecta())
                .collect(Collectors.toList());
        String vista="redirect:/respuestaCorrecta";
        if (Duration.between(partidaPregunta.getHorario(), LocalDateTime.now()).getSeconds() > 10
                || pyrs.isEmpty()) {
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

    @Override
    public void desactivarTodasLasPartidasDelUsuario(Integer id) {
        List<Partida> partidas=repositorioObtener.obtenerPartidasPorUsuario(id).
                                stream().filter(p -> p.getActiva()==true).collect(Collectors.toList());
        for (Partida partida : partidas) {
            partida.setActiva(false);
            repositorioActualizar.actualizarPartida(partida);
        }
    }
}
