package com.project.dominio.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String mail;
    private String password;
    private String username;
    private String fotoPerfil;
    private Integer cantidadTrampitas;
    private Time horarioInstitucion;
    @ManyToOne
    private Sexo sexo;
    @ManyToOne
    private Ciudad ciudad;
    @ManyToOne
    private GrupoEtario grupoEtario;
    @ManyToOne
    private Nivel nivel;
    @ManyToOne
    private Institucion institucion;
    @ManyToOne
    private Rol rol;
}
