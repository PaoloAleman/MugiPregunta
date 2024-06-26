package com.project.presentacion;

import com.project.dominio.entidades.Usuario;
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

import javax.servlet.http.HttpSession;
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

    @RequestMapping("/validarMail")
    public ModelAndView validarMail(HttpSession session){
        return new ModelAndView("validarMail");
    }

    @RequestMapping("/historialPartidas")
    public ModelAndView historialPartidas(HttpSession session){
        ModelMap model=new ModelMap();
        try {
            Usuario usuario=servicioObtener.obtenerUsuarioPorID((Integer) session.getAttribute("idUsuario"));
            model.put("usuario",usuario);
            model.put("partidas",servicioObtener.obtenerPartidasPorUsuario(usuario.getId()));
        } catch (UsuarioInexistenteException e) {
            return new ModelAndView("redirect:/login",model);
        }
        return new ModelAndView("historial/partidas",model);
    }

    @RequestMapping("/ranking")
    public ModelAndView ranking(HttpSession session){
        ModelMap model=new ModelMap();
        try {
            model.put("usuario",servicioObtener.obtenerUsuarioPorID((Integer) session.getAttribute("idUsuario")));
            model.put("usuarios",servicioObtener.obtenerUsuariosOrdenadosPorPuntaje());
        } catch (UsuarioInexistenteException e) {
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("ranking",model);
    }

    @RequestMapping("/perfil")
    public ModelAndView perfil(@RequestParam("idUsuario")Integer idUsuario, HttpSession session){
        ModelMap model=new ModelMap();
        try {
            model.put("usuarioBuscado",servicioObtener.obtenerUsuarioPorID(idUsuario));
            model.put("usuario",servicioObtener.obtenerUsuarioPorID((Integer) session.getAttribute("idUsuario")));
            model.put("cantidadPartidasJugadas",servicioObtener.obtenerPartidasPorUsuario(idUsuario).size());
        } catch (UsuarioInexistenteException e) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("perfil",model);
    }
}
