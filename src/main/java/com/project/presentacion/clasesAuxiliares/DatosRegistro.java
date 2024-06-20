package com.project.presentacion.clasesAuxiliares;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DatosRegistro {

    private String nombre;
    private LocalDate fechaNacimiento;
    private Integer idSexo;
    private Integer idCiudad;
    private String username;
    private String password;
    private String repetirPassword;
    private String mail;

}
