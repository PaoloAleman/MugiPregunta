package com.project.dominio.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Duelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean aceptada;
    @ManyToOne
    private Usuario usuarioLocal;
    @ManyToOne
    private Usuario usuarioVisitante;
    @ManyToOne
    private Usuario usuarioGanador;

}
