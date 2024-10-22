package com.git.gestor_academico.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlunoRequestDTO {

    private Long registroAluno;
    private String nome;
    private String turma;
    private Long cursoId;
    private String telefone;

}
