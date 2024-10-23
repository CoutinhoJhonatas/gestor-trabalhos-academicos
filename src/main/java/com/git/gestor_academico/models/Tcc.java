package com.git.gestor_academico.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_tcc")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tcc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String linkDocs;
    private String resumoProposta;

    @ManyToOne
    @JoinColumn(name = "orientador_id")
    private Orientador orientador;

    @OneToMany(mappedBy = "tcc")
    private List<Aluno> integrantes;

}
