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

    @Override
    public Nivel obtenerNivelPorNombre(String nombre) {
        return (Nivel) sessionFactory.getCurrentSession().
                createQuery("FROM Nivel where nombre= :nombre").
                setParameter("nombre",nombre).setMaxResults(1).uniqueResult();
    }

    @Override
    public Usuario obtenerUsuarioPorID(Integer idUsuario) {
        return (Usuario) sessionFactory.getCurrentSession().
                createQuery("FROM Usuario where id= :id").
                setParameter("id",idUsuario).setMaxResults(1).uniqueResult();
    }

    @Override
    public List<Usuario> obtenerUsuarioOrdenadosPorPuntaje() {
        return (List<Usuario>) sessionFactory.getCurrentSession().
                createQuery("FROM Usuario ORDER BY puntaje").list();
    }

    @Override
    public Pregunta obtenerPreguntaRandom() {
        return (Pregunta) sessionFactory.getCurrentSession().
                createQuery("FROM Pregunta ORDER BY rand()").setMaxResults(1).uniqueResult();
    }

    @Override
    public List<PyR> obtenerRespuestasDePregunta(Integer id) {
        return sessionFactory.getCurrentSession().
                createQuery("FROM PyR where pregunta.id= :id order by rand()").setParameter("id",id).list();
    }

    @Override
    public Partida obtenerPartidaPorID(Integer idPartida) {
        return (Partida) sessionFactory.getCurrentSession().
                createQuery("FROM Partida where id= :id").
                setParameter("id",idPartida).setMaxResults(1).uniqueResult();
    }

    @Override
    public Partida obtenerPartidaActivaDelUsuario(Integer idUsuario) {
        return (Partida) sessionFactory.getCurrentSession().
                createQuery("from Partida where usuario.id= :id and activa= :activa").
                setParameter("id",idUsuario).setParameter("activa",true).
                setMaxResults(1).uniqueResult();
    }

    @Override
    public PartidaPregunta obtenerUltimaPreguntaDeLaPartida(Integer id) {
        return (PartidaPregunta) sessionFactory.getCurrentSession().
                createQuery("from PartidaPregunta where partida.id= :id order by id desc").
                setParameter("id",id).setMaxResults(1).uniqueResult();
    }

    @Override
    public PyR obtenerRespuestaCorrectaDePregunta(Pregunta pregunta) {
        return (PyR) sessionFactory.getCurrentSession().
                createQuery("FROM PyR where pregunta.id= :id and esCorrecta= :esCorrecta").
                setParameter("id",pregunta.getId()).setParameter("esCorrecta",true).
                setMaxResults(1).uniqueResult();
    }

    @Override
    public Partida obtenerUltimaPartidaDelUsuario(Integer id) {
        return (Partida) sessionFactory.getCurrentSession().
                createQuery("FROM Partida where usuario.id= :id order by id desc").
                setParameter("id",id).setMaxResults(1).uniqueResult();
    }

    @Override
    public List<Partida> obtenerPartidasPorUsuario(Integer idUsuario) {
        return sessionFactory.getCurrentSession().
                createQuery("from Partida where usuario.id= :id").
                setParameter("id",idUsuario).list();
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return sessionFactory.getCurrentSession().createQuery("from Usuario").list();
    }
}
