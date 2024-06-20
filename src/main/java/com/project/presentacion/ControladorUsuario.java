package com.project.presentacion;

import com.project.dominio.excepcion.*;
import com.project.dominio.servicios.ServicioCrear;
import com.project.dominio.servicios.ServicioEliminar;
import com.project.dominio.servicios.ServicioObtener;
import com.project.presentacion.clasesAuxiliares.DatosRegistro;
import com.project.dominio.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.LocalDate;

@Controller
public class ControladorUsuario {
    private ServicioCrear servicioCrear;
    private ServicioObtener servicioObtener;
    private ServicioEliminar servicioEliminar;
    private ServicioUsuario servicioUsuario;
    @Autowired
    public ControladorUsuario(ServicioCrear servicioCrear, ServicioObtener servicioObtener, ServicioEliminar servicioEliminar,ServicioUsuario servicioUsuario) {
        this.servicioCrear = servicioCrear;
        this.servicioObtener = servicioObtener;
        this.servicioEliminar = servicioEliminar;
        this.servicioUsuario = servicioUsuario;
    }

    @RequestMapping("/registro")
    public ModelAndView registro(){
        ModelMap model=new ModelMap();
        model.put("datosRegistro",new DatosRegistro());
        model.put("ciudades",servicioObtener.obtenerCiudades());
        model.put("sexos",servicioObtener.obtenerSexos());
        return new ModelAndView("registro",model);
    }

    @RequestMapping(value = "/registro", method = RequestMethod.POST)
    public ModelAndView registro(@ModelAttribute("datosRegistro") DatosRegistro datosRegistro) {
        ModelMap model = new ModelMap();
        try {
            servicioUsuario.validarQueNoHayCamposVacios(datosRegistro);
            servicioUsuario.validarQueLaCiudadExiste(datosRegistro.getIdCiudad());
            servicioUsuario.validarQueElSexoExiste(datosRegistro.getIdSexo());
            servicioUsuario.validarQueLaFechaDeNacimientoEsLogica(LocalDate.parse(datosRegistro.getFechaNacimiento()));
            servicioUsuario.validarQueNoExisteUnUsuarioConEseUsername(datosRegistro.getUsername());
            servicioUsuario.validarQueNoExisteUnUsuarioConEseMail(datosRegistro.getMail());
            servicioUsuario.validarQueLasPasswordsSonIguales(datosRegistro.getPassword(), datosRegistro.getRepetirPassword());
            servicioUsuario.validarFormatoDeLaImagen(datosRegistro.getImg());
            servicioCrear.crearUsuario(datosRegistro);
        } catch (SexoInexistenteException | FechaNacimientoInvalidaException | CiudadInexistenteException |
                 UsuarioExistenteException | PasswordsDiferentesException | CampoVacioException |
                 FormatoImagenInvalidoException | IOException e) {
            model.put("mensaje", e.getMessage());
            model.put("ciudades",servicioObtener.obtenerCiudades());
            model.put("sexos",servicioObtener.obtenerSexos());
            return new ModelAndView("registro", model);
        }
        return new ModelAndView("redirect:/login");
    }
}
