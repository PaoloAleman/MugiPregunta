package com.project.dominio.servicios;

import com.project.infraestructura.RepositorioObtener;
import com.project.infraestructura.RepositorioPartida;
import com.project.infraestructura.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioPartida")
@Transactional
public class ServicioPartidaImpl implements ServicioPartida {
    private RepositorioPartida repositorioPartida;
    private RepositorioObtener repositorioObtener;
    @Autowired
    public ServicioPartidaImpl(RepositorioPartida repositorioPartida,RepositorioObtener repositorioObtener) {
        this.repositorioPartida = repositorioPartida;
        this.repositorioObtener = repositorioObtener;
    }
}
