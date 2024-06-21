package com.project.presentacion;

import com.project.dominio.entidades.*;
import com.project.dominio.excepcion.PartidaInexistenteException;
import com.project.dominio.excepcion.UsuarioInexistenteException;
import com.project.dominio.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ControladorPartida {
    private ServicioCrear servicioCrear;
    private ServicioObtener servicioObtener;
    private ServicioEliminar servicioEliminar;
    private ServicioPartida servicioPartida;
    @Autowired
    public ControladorPartida(ServicioCrear servicioCrear, ServicioObtener servicioObtener, ServicioEliminar servicioEliminar,ServicioPartida servicioPartida) {
        this.servicioCrear = servicioCrear;
        this.servicioObtener = servicioObtener;
        this.servicioEliminar = servicioEliminar;
        this.servicioPartida = servicioPartida;
    }

    @RequestMapping("/comenzandoPartida")
    public ModelAndView comenzandoPartida(HttpSession session){
        ModelMap model=new ModelMap();
        try {
            Usuario usuario=servicioObtener.obtenerUsuarioPorID((Integer) session.getAttribute("idUsuario"));
            servicioCrear.crearPartida(new Partida(usuario));
        } catch (UsuarioInexistenteException e) {
            return new ModelAndView("redirect:/login",model);
        }
        return new ModelAndView("redirect:/partida",model);
    }
    @RequestMapping("/partida")
    public ModelAndView partida(HttpSession session){
        ModelMap model=new ModelMap();
        try {
            Pregunta pregunta=servicioObtener.obtenerPreguntaRandom();
            Partida partida=servicioObtener.obtenerPartidaActivaDelUsuario((Integer) session.getAttribute("idUsuario"));
            servicioCrear.crearPartidaPregunta(new PartidaPregunta(partida,pregunta));
            model.put("pregunta",pregunta);
            model.put("pyrs",servicioObtener.obtenerRespuestasDePregunta(pregunta.getId()));
        } catch (PartidaInexistenteException e) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("partida",model);
    }
    @RequestMapping("/respuesta")
    public ModelAndView respuesta(@RequestParam("idRespuesta") Integer idRespuesta, HttpSession session){
        ModelMap model=new ModelMap();
        String vista="";
        try {
            Partida partida=servicioObtener.obtenerPartidaActivaDelUsuario((Integer) session.getAttribute("idUsuario"));
            PartidaPregunta partidaPregunta=servicioObtener.obtenerUltimaPreguntaDeLaPartida(partida.getId());
            vista=servicioPartida.definirSiLaRespuestaEsCorrecta(idRespuesta,partidaPregunta.getPregunta());
        } catch (PartidaInexistenteException e) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView(vista,model);
    }
}
