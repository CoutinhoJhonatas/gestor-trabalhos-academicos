package com.git.gestor_academico.models;

import com.git.gestor_academico.models.enums.Disponibilidades;
import com.git.gestor_academico.models.enums.Titulacao;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "tb_orientador")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Orientador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricula;
    private String nome;
    private String telefone;
    private String areaConhecimento;

    @Enumerated(EnumType.STRING)
    private Titulacao titulacao;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Disponibilidades> disponibilidades;

}
