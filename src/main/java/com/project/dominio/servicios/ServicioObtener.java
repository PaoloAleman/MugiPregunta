package com.project.dominio.servicios;

import com.project.dominio.entidades.*;
import com.project.dominio.excepcion.CiudadInexistenteException;
import com.project.dominio.excepcion.PartidaInexistenteException;
import com.project.dominio.excepcion.SexoInexistenteException;
import com.project.dominio.excepcion.UsuarioInexistenteException;

import java.util.List;

public interface ServicioObtener {

    List<Ciudad> obtenerCiudades();
    List<Sexo> obtenerSexos();

    Sexo obtenerSexoPorID(Integer idSexo) throws SexoInexistenteException;
    Ciudad obtenerCiudadPorID(Integer idCiudad) throws CiudadInexistenteException;

    Usuario obtenerUsuarioPorUsernamePassword(String username, String password) throws UsuarioInexistenteException;

    Usuario obtenerUsuarioPorID(Integer idUsuario) throws UsuarioInexistenteException;

    List<Usuario> obtenerUsuariosOrdenadosPorPuntaje();

    Pregunta obtenerPreguntaRandom();

    List<PyR> obtenerRespuestasDePregunta(Integer id);

    Partida obtenerPartidaPorID(Integer idPartida) throws PartidaInexistenteException;

    Partida obtenerPartidaActivaDelUsuario(Integer idUsuario) throws PartidaInexistenteException;

    PartidaPregunta obtenerUltimaPreguntaDeLaPartida(Integer id);
}
