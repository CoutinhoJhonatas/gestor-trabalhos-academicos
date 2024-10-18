package com.git.gestor_academico.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TccDto {

    private Long id;
    private String titulo;
    private List<AlunoDto> integrantes;
    private OrientadorDto orientador;

}
