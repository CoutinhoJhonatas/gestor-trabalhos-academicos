package com.git.gestor_academico.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlunoDto {

    private Long registroAluno;
    private String nome;
    private String turma;
    private String curso;
    private String telefone;

}
