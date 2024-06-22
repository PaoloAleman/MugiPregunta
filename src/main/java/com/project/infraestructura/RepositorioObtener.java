package com.project.infraestructura;

import com.project.dominio.entidades.*;

import java.util.List;

public interface RepositorioObtener {
    Sexo obtenerSexoPorID(Integer idSexo);

    List<Ciudad> obtenerCiudades();

    List<Sexo> obtenerSexos();

    Ciudad obtenerCiudadPorID(Integer idCiudad);

    Usuario obtenerUsuarioPorUsername(String username);

    Usuario obtenerUsuarioPorMail(String mail);

    GrupoEtario obtenerGrupoEtarioPorNombre(String nombre);

    Rol obtenerRolPorNombre(String nombre);

    Nivel obtenerNivelPorNombre(String nombre);

    Usuario obtenerUsuarioPorID(Integer idUsuario);

    List<Usuario> obtenerUsuarioOrdenadosPorPuntaje();

    Pregunta obtenerPreguntaRandom();

    List<PyR> obtenerRespuestasDePregunta(Integer id);

    Partida obtenerPartidaPorID(Integer idPartida);

    Partida obtenerPartidaActivaDelUsuario(Integer idUsuario);

    PartidaPregunta obtenerUltimaPreguntaDeLaPartida(Integer id);

    PyR obtenerRespuestaCorrectaDePregunta(Pregunta pregunta);

    Partida obtenerUltimaPartidaDelUsuario(Integer id);

    List<Partida> obtenerPartidasPorUsuario(Integer idUsuario);

    List<Usuario> obtenerUsuarios();
}
