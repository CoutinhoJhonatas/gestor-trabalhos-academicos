package com.git.gestor_academico.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CoordenadorResponseDTO {

    private Long matricula;
    private String nome;
    private String telefone;
    private String role;
    private Boolean ativo;

}
