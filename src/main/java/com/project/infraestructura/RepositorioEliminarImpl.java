package com.project.infraestructura;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioEliminar")
public class RepositorioEliminarImpl implements RepositorioEliminar{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioEliminarImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }
}
