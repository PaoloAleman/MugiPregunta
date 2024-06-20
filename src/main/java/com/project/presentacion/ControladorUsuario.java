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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/registro",method = RequestMethod.POST)
    public ModelAndView registro(@RequestBody DatosRegistro datos){
        ModelMap model=new ModelMap();
        try {
            servicioUsuario.validarQueNoHayCamposVacios(datos);
            servicioUsuario.validarQueLaCiudadExiste(datos.getIdCiudad());
            servicioUsuario.validarQueElSexoExiste(datos.getIdSexo());
            servicioUsuario.validarQueLaFechaDeNacimientoEsLogica(datos.getFechaNacimiento());
            servicioUsuario.validarQueNoExisteUnUsuarioConEseUsername(datos.getUsername());
            servicioUsuario.validarQueNoExisteUnUsuarioConEseMail(datos.getMail());
            servicioUsuario.validarQueLasPasswordsSonIguales(datos.getPassword(),datos.getRepetirPassword());
            servicioUsuario.hashearPassword(datos.getPassword());
            servicioCrear.crearUsuario(datos);
            model.put("mensaje","Estamos creado su usuario, espere unos segundos!");
            model.put("flag",1);
        } catch (SexoInexistenteException | FechaNacimientoInvalidaException | CiudadInexistenteException |
                 UsuarioExistenteException | PasswordsDiferentesException | CampoVacioException e) {
            model.put("mensaje",e.getMessage());
        }
        return new ModelAndView("popUp/mensaje",model);
    }
}
