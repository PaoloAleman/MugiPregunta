package com.project.infraestructura;

import com.project.dominio.entidades.Partida;
import com.project.dominio.entidades.PartidaPregunta;
import com.project.dominio.entidades.Usuario;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioActualizar")
public class RepositorioActualizarImpl implements RepositorioActualizar {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioActualizarImpl(SessionFactory sessionFactory){ this.sessionFactory=sessionFactory; }

    @Override
    public void actualizarPartida(Partida partida) {
        sessionFactory.getCurrentSession().saveOrUpdate(partida);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        sessionFactory.getCurrentSession().saveOrUpdate(usuario);
    }

    @Override
    public void actualizarPartidaPregunta(PartidaPregunta partidaPregunta) {
        sessionFactory.getCurrentSession().saveOrUpdate(partidaPregunta);
    }
}
