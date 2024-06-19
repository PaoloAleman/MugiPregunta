package com.project.infraestructura;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioObtener")
public class RepositorioObtenerImpl implements RepositorioObtener {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioObtenerImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

}
