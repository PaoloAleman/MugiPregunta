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
}
