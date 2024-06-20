package com.project.dominio.servicios;

import com.project.dominio.entidades.Ciudad;
import com.project.dominio.entidades.Sexo;
import com.project.dominio.entidades.Usuario;
import com.project.dominio.excepcion.*;
import com.project.infraestructura.RepositorioObtener;
import com.project.infraestructura.RepositorioUsuario;
import com.project.presentacion.clasesAuxiliares.DatosRegistro;
import com.project.dominio.excepcion.CampoVacioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Service("servicioUsuario")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

    private RepositorioUsuario repositorioUsuario;
    private RepositorioObtener repositorioObtener;
    @Autowired
    public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario,RepositorioObtener repositorioObtener) {
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioObtener = repositorioObtener;
    }


    @Override
    public void validarQueElSexoExiste(Integer idSexo) throws SexoInexistenteException {
        Sexo sexo= repositorioObtener.obtenerSexoPorID(idSexo);
        if (sexo==null){
            throw new SexoInexistenteException("El sexo ingresado no existe!");
        }
    }

    @Override
    public void validarQueLaFechaDeNacimientoEsLogica(LocalDate fechaNacimiento) throws FechaNacimientoInvalidaException {
        if (fechaNacimiento==null || LocalDate.now().isBefore(fechaNacimiento)){
            throw new FechaNacimientoInvalidaException("La fecha de nacimiento ingresada es inválida!");
        }
    }

    @Override
    public void validarQueLaCiudadExiste(Integer idCiudad) throws CiudadInexistenteException {
        Ciudad ciudad=repositorioObtener.obtenerCiudadPorID(idCiudad);
        if (ciudad==null){
            throw new CiudadInexistenteException("La ciudad ingresada no existe!");
        }
    }

    @Override
    public void validarQueNoExisteUnUsuarioConEseUsername(String username) throws UsuarioExistenteException {
        Usuario usuario=repositorioObtener.obtenerUsuarioPorUsername(username);
        if (usuario!=null) {
            throw new UsuarioExistenteException("Ya existe un usuario con ese username");
        }
    }

    @Override
    public void validarQueNoExisteUnUsuarioConEseMail(String mail) throws UsuarioExistenteException {
        Usuario usuario=repositorioObtener.obtenerUsuarioPorMail(mail);
        if (usuario!=null) {
            throw new UsuarioExistenteException("Ya existe un usuario con ese mail");
        }
    }

    @Override
    public void validarQueLasPasswordsSonIguales(String password, String repetirPassword) throws PasswordsDiferentesException {
        if (!password.equals(repetirPassword)){
            throw new PasswordsDiferentesException("Las passwords ingresadas no coinciden!");
        }
    }

    @Override
    public void hashearPassword(String password) {
        BCrypt.hashpw(password,BCrypt.gensalt());
    }

    @Override
    public void validarQueNoHayCamposVacios(DatosRegistro datos) throws CampoVacioException {
        if (datos.getUsername()=="" || datos.getFechaNacimiento()==null ||
                datos.getMail()=="" || datos.getNombre()=="" || datos.getImg()==null) {
            throw new CampoVacioException("No se permiten campos vacíos!");
        }
    }

    @Override
    public void validarFormatoDeLaImagen(MultipartFile img) throws FormatoImagenInvalidoException {
        String nombreImg=img.getOriginalFilename();
        if (!nombreImg.endsWith(".jpg") && !nombreImg.endsWith(".png") && !nombreImg.endsWith(".jpeg")) {
            throw new FormatoImagenInvalidoException("El formato de la imagen debe ser .png, .jpg o .jpeg");
        }
    }
}
