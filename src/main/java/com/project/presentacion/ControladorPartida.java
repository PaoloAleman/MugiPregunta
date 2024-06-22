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
            servicioPartida.desactivarTodasLasPartidasDelUsuario(usuario.getId());
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
            PartidaPregunta partidaPregunta=servicioObtener.obtenerUltimaPreguntaDeLaPartida(partida.getId());
            if(!servicioPartida.validarSiSeDebeRepetirLaPregunta(partidaPregunta)) {
                model.put("pregunta", pregunta);
                model.put("pyrs", servicioObtener.obtenerRespuestasDePregunta(pregunta.getId()));
                servicioCrear.crearPartidaPregunta(new PartidaPregunta(partida, pregunta));
            }else {
                model.put("pregunta",partidaPregunta.getPregunta());
                model.put("pyrs",servicioObtener.obtenerRespuestasDePregunta(partidaPregunta.getPregunta().getId()));
            }
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
            vista=servicioPartida.definirSiLaRespuestaEsCorrecta(idRespuesta,partidaPregunta);
            servicioPartida.desactivarPreguntaDeLaPartida(partidaPregunta,vista);
        } catch (PartidaInexistenteException e) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView(vista,model);
    }
    @RequestMapping("/respuestaCorrecta")
    public ModelAndView respuestaCorrecta(HttpSession session){
        ModelMap model=new ModelMap();
        try {
            Usuario usuario= servicioObtener.obtenerUsuarioPorID((Integer) session.getAttribute("idUsuario"));
            Partida partida=servicioObtener.obtenerPartidaActivaDelUsuario(usuario.getId());
            PartidaPregunta partidaPregunta=servicioObtener.obtenerUltimaPreguntaDeLaPartida(partida.getId());
            servicioPartida.validarQueSeRespondioLaPregunta(partidaPregunta);
            servicioPartida.actualizarPuntajePartida(partida);
            servicioPartida.actualizarPuntajeUsuario(usuario);
            model.put("partida",partida);
            model.put("pregunta",partidaPregunta.getPregunta());
        } catch (UsuarioInexistenteException e) {
            return new ModelAndView("redirect:/login");
        } catch (PartidaInexistenteException | PreguntaSinResponderException e) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("respuestaCorrecta",model);
    }
    @RequestMapping("/respuestaIncorrecta")
    public ModelAndView respuestaIncorrecta(HttpSession session){
        ModelMap model=new ModelMap();
        try {
            Partida partida=servicioObtener.obtenerPartidaActivaDelUsuario((Integer) session.getAttribute("idUsuario"));
            PartidaPregunta partidaPregunta=servicioObtener.obtenerUltimaPreguntaDeLaPartida(partida.getId());
            servicioPartida.validarQueSeRespondioLaPregunta(partidaPregunta);
            servicioPartida.finalizarPartida(partida);
            model.put("partida",partida);
            model.put("pregunta",partidaPregunta.getPregunta());
            model.put("respuestaCorrecta",servicioObtener.obtenerRespuestaCorrectaDePregunta(partidaPregunta.getPregunta()));
        } catch (PartidaInexistenteException | PreguntaSinResponderException e) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("respuestaIncorrecta",model);
    }
}
