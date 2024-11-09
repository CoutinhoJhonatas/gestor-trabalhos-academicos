package com.git.gestor_academico.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlunoRequestDTO {

    @NotNull(message = "Campo requerido")
    private Long registroAluno;

    @NotBlank(message = "Campo requerido")
    private String nome;

    @NotBlank(message = "Campo requerido")
    private String turma;

    @NotNull(message = "Campo requerido") //TODO verificar no front maneira de vincular o curso no cadastro do aluno
    private Long cursoId;

    @NotBlank(message = "Campo requerido")
    private String telefone;

    @NotNull(message = "Campo requerido")
    private String senha;

    @NotNull(message = "Campo requerido")
    private String email;

}
