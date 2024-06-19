package com.project.presentacion;

import com.project.dominio.servicios.ServicioObtener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorHome {

    private ServicioObtener servicioObtener;

    @Autowired
    public ControladorHome(ServicioObtener servicioObtener){
        this.servicioObtener=servicioObtener;
    }

    @RequestMapping(value = {"/","/home"})
    public ModelAndView home(){
        ModelMap model=new ModelMap();
        return new ModelAndView("home",model);
    }
}