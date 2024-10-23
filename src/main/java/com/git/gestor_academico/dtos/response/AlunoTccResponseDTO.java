package com.git.gestor_academico.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlunoTccResponseDTO {

    private Long registroAluno;
    private String nome;
    private String turma;
    private String telefone;
    private CursoResponseDTO curso;

}
