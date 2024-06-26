package com.project.presentacion;

import com.project.dominio.entidades.Usuario;
import com.project.dominio.excepcion.CampoVacioException;
import com.project.dominio.excepcion.PartidaInexistenteException;
import com.project.dominio.excepcion.UsuarioInexistenteException;
import com.project.dominio.servicios.ServicioCrear;
import com.project.dominio.servicios.ServicioObtener;
import com.project.presentacion.clasesAuxiliares.DatosRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ControladorHome {

    private ServicioObtener servicioObtener;

    @Autowired
    public ControladorHome(ServicioObtener servicioObtener){
        this.servicioObtener=servicioObtener;
    }

    @RequestMapping(value ="/home")
    public ModelAndView home(HttpSession session){
        ModelMap model=new ModelMap();
        try {
            Usuario usuario=servicioObtener.obtenerUsuarioPorID((Integer) session.getAttribute("idUsuario"));
            model.put("usuarios",servicioObtener.obtenerUsuariosOrdenadosPorPuntaje());
            model.put("usuario",usuario);
            model.put("ultimaPartida",servicioObtener.obtenerUltimaPartidaDelUsuario(usuario.getId()));
            model.put("partidaActiva",servicioObtener.obtenerPartidaActivaDelUsuario(usuario.getId()));
        } catch (UsuarioInexistenteException e) {
            return new ModelAndView("redirect:/login",model);
        } catch (PartidaInexistenteException e) {
            model.put("mensaje",e.getMessage());
        }
        return new ModelAndView("home",model);
    }
    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("login_username")String username,
                              @RequestParam("login_password")String password, HttpSession session){
        ModelMap model=new ModelMap();
        try {
            validarQueNoHayCamposVacios(username,password);
            Usuario usuario=servicioObtener.obtenerUsuarioPorUsernamePassword(username,password);
            session.setAttribute("idUsuario",usuario.getId());
            session.setMaxInactiveInterval(1800);
        } catch (UsuarioInexistenteException | CampoVacioException e) {
            model.put("mensaje",e.getMessage());
            return new ModelAndView("login",model);
        }
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.invalidate();
        return new ModelAndView("redirect:/login");
    }

    private void validarQueNoHayCamposVacios(String username, String password) throws CampoVacioException {
        if (username=="" || password=="") {
            throw new CampoVacioException("No se permiten campos vacíos");
        }
    }
}