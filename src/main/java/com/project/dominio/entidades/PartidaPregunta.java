package com.project.dominio.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartidaPregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean resultado;
    private LocalDateTime horario;
    @ManyToOne
    private Partida partida;
    @ManyToOne
    private Pregunta pregunta;

    public PartidaPregunta(Partida partida, Pregunta pregunta) {
        this.partida = partida;
        this.pregunta = pregunta;
        this.horario = LocalDateTime.now();
    }
}
