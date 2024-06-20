package com.project.dominio.servicios;

import com.project.dominio.excepcion.*;
import com.project.presentacion.clasesAuxiliares.DatosRegistro;

import java.time.LocalDate;

public interface ServicioUsuario {
    void validarQueElSexoExiste(Integer idSexo) throws SexoInexistenteException;

    void validarQueLaFechaDeNacimientoEsLogica(LocalDate fechaNacimiento) throws FechaNacimientoInvalidaException;

    void validarQueLaCiudadExiste(Integer idCiudad) throws CiudadInexistenteException;

    void validarQueNoExisteUnUsuarioConEseUsername(String username) throws UsuarioExistenteException;

    void validarQueNoExisteUnUsuarioConEseMail(String mail) throws UsuarioExistenteException;

    void validarQueLasPasswordsSonIguales(String password, String repetirPassword) throws PasswordsDiferentesException;

    void hashearPassword(String password);

    void validarQueNoHayCamposVacios(DatosRegistro datos) throws CampoVacioException;
}
