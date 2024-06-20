package com.project.infraestructura;

import com.project.dominio.entidades.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioObtener")
public class RepositorioObtenerImpl implements RepositorioObtener {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioObtenerImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public Sexo obtenerSexoPorID(Integer idSexo) {
        return (Sexo) sessionFactory.getCurrentSession().
                createQuery("from Sexo where id= :id").
                setParameter("id",idSexo).setMaxResults(1).uniqueResult();
    }

    @Override
    public List<Ciudad> obtenerCiudades() {
        return sessionFactory.getCurrentSession().createQuery("FROM Ciudad").list();
    }

    @Override
    public List<Sexo> obtenerSexos() {
        return sessionFactory.getCurrentSession().createQuery("FROM Sexo ").list();
    }

    @Override
    public Ciudad obtenerCiudadPorID(Integer idCiudad) {
        return (Ciudad) sessionFactory.getCurrentSession().
                createQuery("FROM Ciudad where id= :id").
                setParameter("id",idCiudad).setMaxResults(1).uniqueResult();
    }

    @Override
    public Usuario obtenerUsuarioPorUsername(String username) {
        return (Usuario) sessionFactory.getCurrentSession()
                .createQuery("FROM Usuario where username= :username").
                setParameter("username",username).setMaxResults(1).uniqueResult();
    }

    @Override
    public Usuario obtenerUsuarioPorMail(String mail) {
        return (Usuario) sessionFactory.getCurrentSession()
                .createQuery("FROM Usuario where mail= :mail").
                setParameter("mail",mail).setMaxResults(1).uniqueResult();
    }

    @Override
    public GrupoEtario obtenerGrupoEtarioPorNombre(String nombre) {
        return (GrupoEtario) sessionFactory.getCurrentSession().
                createQuery("from GrupoEtario where nombre= :nombre").
                setParameter("nombre",nombre).setMaxResults(1).uniqueResult();
    }

    @Override
    public Rol obtenerRolPorNombre(String nombre) {
        return (Rol) sessionFactory.getCurrentSession().
                createQuery("from Rol where nombre= :nombre").
                setParameter("nombre",nombre).setMaxResults(1).uniqueResult();
    }
}
