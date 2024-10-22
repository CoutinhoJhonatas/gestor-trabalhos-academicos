package com.git.gestor_academico.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlunoResponseDTO {

    private Long registroAluno;
    private String nome;
    private String turma;
    private String telefone;
    //private String role;
    private CursoResponseDTO curso;

}
