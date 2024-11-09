package com.git.gestor_academico.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_aluno")
@RequiredArgsConstructor
@Getter
@Setter
public class Aluno {

    @Id
    private Long registroAluno;
    private String nome;
    private String turma;
    private String telefone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "tcc_id")
    private Tcc tcc;

}
