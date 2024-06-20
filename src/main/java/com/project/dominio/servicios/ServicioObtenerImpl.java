package com.project.dominio.servicios;

import com.project.dominio.entidades.Ciudad;
import com.project.dominio.entidades.Sexo;
import com.project.infraestructura.RepositorioObtener;
import com.project.infraestructura.RepositorioObtenerImpl;
import com.project.dominio.excepcion.CiudadInexistenteException;
import com.project.dominio.excepcion.SexoInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioObtener")
@Transactional
public class ServicioObtenerImpl implements ServicioObtener {

    private RepositorioObtener repositorioObtener;

    @Autowired
    public ServicioObtenerImpl(RepositorioObtenerImpl repositorioObtener){
        this.repositorioObtener=repositorioObtener;
    }

    @Override
    public List<Ciudad> obtenerCiudades() {
        return repositorioObtener.obtenerCiudades();
    }

    @Override
    public Sexo obtenerSexoPorID(Integer idSexo) throws SexoInexistenteException {
        Sexo sexo=repositorioObtener.obtenerSexoPorID(idSexo);
        if (sexo.equals(null)){
            throw new SexoInexistenteException("El sexo ingresado no existe!");
        }
        return sexo;
    }

    @Override
    public Ciudad obtenerCiudadPorID(Integer idCiudad) throws CiudadInexistenteException {
        Ciudad ciudad=repositorioObtener.obtenerCiudadPorID(idCiudad);
        if (ciudad.equals(null)){
            throw new CiudadInexistenteException("La ciudad ingresada no existe!");
        }
        return ciudad;
    }

    @Override
    public List<Sexo> obtenerSexos() {
        return repositorioObtener.obtenerSexos();
    }
}
