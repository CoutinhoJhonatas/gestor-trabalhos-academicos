package com.git.gestor_academico.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_coordenador")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coordenador {

    @Id
    private Long matricula;
    private String nome;
    private String telefone;
    private Boolean ativo;
    private String email;

    @OneToMany(mappedBy = "coordenador")
    private List<Tcc> tccs;

}
