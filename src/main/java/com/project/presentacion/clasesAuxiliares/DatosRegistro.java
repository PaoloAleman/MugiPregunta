package com.project.presentacion.clasesAuxiliares;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DatosRegistro {

    private String nombre;
    private String fechaNacimiento;
    private Integer idSexo;
    private Integer idCiudad;
    private String username;
    private String password;
    private String repetirPassword;
    private String mail;
    private MultipartFile img;

}
