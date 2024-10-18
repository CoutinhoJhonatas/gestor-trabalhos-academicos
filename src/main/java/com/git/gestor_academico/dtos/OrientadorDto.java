package com.git.gestor_academico.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrientadorDto {

    private Long matricula;
    private String nome;
    private String telefone;
    private String areaConhecimento;
    private String titulacao;
    private Set<String> disponibilidades;

}
