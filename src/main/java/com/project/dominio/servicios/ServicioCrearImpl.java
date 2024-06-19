package com.project.dominio.servicios;

import com.project.infraestructura.RepositorioCrear;
import com.project.infraestructura.RepositorioCrearImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioCrear")
@Transactional
public class ServicioCrearImpl implements ServicioCrear {
    private RepositorioCrear repositorioCrear;
    @Autowired
    public ServicioCrearImpl(RepositorioCrearImpl repositorioCrear){
        this.repositorioCrear=repositorioCrear;
    }
}
