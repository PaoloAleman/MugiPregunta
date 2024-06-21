package com.project.dominio.servicios;

import com.project.dominio.entidades.Ciudad;
import com.project.dominio.entidades.Sexo;
import com.project.dominio.entidades.Usuario;
import com.project.dominio.excepcion.UsuarioInexistenteException;
import com.project.infraestructura.RepositorioObtener;
import com.project.infraestructura.RepositorioObtenerImpl;
import com.project.dominio.excepcion.CiudadInexistenteException;
import com.project.dominio.excepcion.SexoInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioObtener")
@Transactional
public class ServicioObtenerImpl implements ServicioObtener {

    private RepositorioObtener repositorioObtener;

    @Autowired
    public ServicioObtenerImpl(RepositorioObtenerImpl repositorioObtener){
        this.repositorioObtener=repositorioObtener;
    }

    @Override
    public List<Ciudad> obtenerCiudades() {
        return repositorioObtener.obtenerCiudades();
    }

    @Override
    public Sexo obtenerSexoPorID(Integer idSexo) throws SexoInexistenteException {
        Sexo sexo=repositorioObtener.obtenerSexoPorID(idSexo);
        if (sexo.equals(null)){
            throw new SexoInexistenteException("El sexo ingresado no existe!");
        }
        return sexo;
    }

    @Override
    public Ciudad obtenerCiudadPorID(Integer idCiudad) throws CiudadInexistenteException {
        Ciudad ciudad=repositorioObtener.obtenerCiudadPorID(idCiudad);
        if (ciudad.equals(null)){
            throw new CiudadInexistenteException("La ciudad ingresada no existe!");
        }
        return ciudad;
    }

    @Override
    public Usuario obtenerUsuarioPorUsernamePassword(String username, String password) throws UsuarioInexistenteException {
        Usuario usuario = repositorioObtener.obtenerUsuarioPorUsername(username);
        validarQueHayUnUsuarioConEseUsername(usuario);
        validarQueLaPasswordEsCorrecta(password, usuario);
        return usuario;
    }
    @Override
    public Usuario obtenerUsuarioPorID(Integer idUsuario) throws UsuarioInexistenteException {
        Usuario usuario=repositorioObtener.obtenerUsuarioPorID(idUsuario);
        if (usuario==null) {
            throw new UsuarioInexistenteException("No existe un usuario con ese ID");
        }
        return usuario;
    }

    @Override
    public List<Usuario> obtenerUsuariosOrdenadosPorPuntaje() {
        return repositorioObtener.obtenerUsuarioOrdenadosPorPuntaje();
    }

    @Override
    public List<Sexo> obtenerSexos() {
        return repositorioObtener.obtenerSexos();
    }

    private void validarQueLaPasswordEsCorrecta(String password, Usuario usuario) throws UsuarioInexistenteException {
        if (!BCrypt.checkpw(password, usuario.getPassword())) {
            throw new UsuarioInexistenteException("Contraseña incorrecta!");
        }
    }

    private void validarQueHayUnUsuarioConEseUsername(Usuario usuario) throws UsuarioInexistenteException {
        if (usuario ==null) {
            throw new UsuarioInexistenteException("Username incorrecto!");
        }
    }
}
