package com.project.presentacion;

import com.project.dominio.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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

    @RequestMapping("/partida")
    public ModelAndView partida(HttpSession session){
        return null;
    }
}
