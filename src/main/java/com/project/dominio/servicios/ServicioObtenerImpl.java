package com.project.dominio.servicios;

import com.project.infraestructura.RepositorioObtener;
import com.project.infraestructura.RepositorioObtenerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioObtener")
@Transactional
public class ServicioObtenerImpl implements ServicioObtener {

    private RepositorioObtener repositorioObtener;

    @Autowired
    public ServicioObtenerImpl(RepositorioObtenerImpl repositorioObtener){
        this.repositorioObtener=repositorioObtener;
    }

}
