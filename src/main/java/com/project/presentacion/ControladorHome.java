package com.project.presentacion;

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

@Controller
public class ControladorHome {

    private ServicioObtener servicioObtener;

    @Autowired
    public ControladorHome(ServicioObtener servicioObtener){
        this.servicioObtener=servicioObtener;
    }

    @RequestMapping(value ="/home")
    public ModelAndView home(){
        ModelMap model=new ModelMap();
        return new ModelAndView("home",model);
    }
    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("login_username")String username,
                              @RequestParam("login_password")String password){
        return new ModelAndView("redirect:/home");
    }
}