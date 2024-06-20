package com.project.dominio.servicios;

import com.project.dominio.entidades.*;
import com.project.infraestructura.RepositorioCrear;
import com.project.infraestructura.RepositorioCrearImpl;
import com.project.infraestructura.RepositorioObtener;
import com.project.presentacion.clasesAuxiliares.DatosRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

@Service("servicioCrear")
@Transactional
public class ServicioCrearImpl implements ServicioCrear {
    private RepositorioCrear repositorioCrear;
    private RepositorioObtener repositorioObtener;
    @Autowired
    public ServicioCrearImpl(RepositorioCrearImpl repositorioCrear, RepositorioObtener repositorioObtener){
        this.repositorioCrear=repositorioCrear;
        this.repositorioObtener = repositorioObtener;
    }

    @Override
    public void crearUsuario(DatosRegistro datos) {
        Ciudad ciudad=repositorioObtener.obtenerCiudadPorID(datos.getIdCiudad());
        Sexo sexo= repositorioObtener.obtenerSexoPorID(datos.getIdSexo());
        Integer edad= Period.between(datos.getFechaNacimiento(),LocalDate.now()).getYears();
        GrupoEtario grupoEtario= definirGrupoEtario(edad);
        Rol rol=repositorioObtener.obtenerRolPorNombre("Jugador");
        repositorioCrear.crearUsuario(new Usuario(datos.getNombre(), datos.getFechaNacimiento(),
                datos.getMail(), BCrypt.hashpw(datos.getPassword(),BCrypt.gensalt()),datos.getUsername(),"Hola mundo",
                sexo,ciudad,grupoEtario,rol));
    }

    private GrupoEtario definirGrupoEtario(Integer edad) {
        return (edad < 18) ? repositorioObtener.obtenerGrupoEtarioPorNombre("Nene") :
                (edad >= 18 && edad <= 60) ? repositorioObtener.obtenerGrupoEtarioPorNombre("Adolescente") :
                        repositorioObtener.obtenerGrupoEtarioPorNombre("Jubilado");
    }
}
