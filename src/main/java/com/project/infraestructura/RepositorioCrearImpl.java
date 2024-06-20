package com.project.infraestructura;

import com.project.dominio.entidades.Usuario;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioCrear")
public class RepositorioCrearImpl implements RepositorioCrear {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCrearImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public void crearUsuario(Usuario usuario) {
        sessionFactory.getCurrentSession().save(usuario);
    }
}
