package com.git.gestor_academico.models;

import com.git.gestor_academico.models.enums.Disponibilidades;
import com.git.gestor_academico.models.enums.Titulacao;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_orientador")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Orientador {

    @Id
    private Long matricula;
    private String nome;
    private String telefone;
    private String areaConhecimento;

    @Enumerated(EnumType.STRING)
    private Titulacao titulacao;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "tb_orientador_disponibilidades")
    private Set<Disponibilidades> disponibilidades;

    @OneToMany(mappedBy = "orientador")
    private List<Tcc> tccs;

}
