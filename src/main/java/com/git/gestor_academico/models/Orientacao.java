package com.git.gestor_academico.models;

import com.git.gestor_academico.models.enums.OrientacaoStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_orientacao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Orientacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrientacaoStatus status;

    private String motivoRecusado;

    @ManyToOne
    @JoinColumn(name = "orientador_id")
    private Orientador orientador;

    @OneToOne
    @MapsId
    private Tcc tcc;

    @OneToMany(mappedBy = "orientacao")
    private List<Checkpoint> checkpoints;

}
