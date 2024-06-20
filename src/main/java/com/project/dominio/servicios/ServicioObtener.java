package com.project.dominio.servicios;

import com.project.dominio.entidades.Ciudad;
import com.project.dominio.entidades.Sexo;
import com.project.dominio.entidades.Usuario;
import com.project.dominio.excepcion.CiudadInexistenteException;
import com.project.dominio.excepcion.SexoInexistenteException;
import com.project.dominio.excepcion.UsuarioInexistenteException;

import java.util.List;

public interface ServicioObtener {

    List<Ciudad> obtenerCiudades();
    List<Sexo> obtenerSexos();

    Sexo obtenerSexoPorID(Integer idSexo) throws SexoInexistenteException;
    Ciudad obtenerCiudadPorID(Integer idCiudad) throws CiudadInexistenteException;

    Usuario obtenerUsuarioPorUsernamePassword(String username, String password) throws UsuarioInexistenteException;
}
