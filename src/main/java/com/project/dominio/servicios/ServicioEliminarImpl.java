package com.project.dominio.servicios;

import com.project.infraestructura.RepositorioEliminar;
import com.project.infraestructura.RepositorioEliminarImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioEliminar")
@Transactional
public class ServicioEliminarImpl implements ServicioEliminar {
    private RepositorioEliminar repositorioEliminar;

    @Autowired
    public ServicioEliminarImpl(RepositorioEliminarImpl repositorioEliminar){
        this.repositorioEliminar=repositorioEliminar;
    }
}
