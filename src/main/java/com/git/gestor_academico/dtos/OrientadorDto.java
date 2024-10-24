package com.git.gestor_academico.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Campo requerido")
    private Long matricula;

    @NotBlank(message = "Campo requerido")
    private String nome;

    @NotBlank(message = "Campo requerido")
    private String telefone;

    @NotBlank(message = "Campo requerido")
    private String areaConhecimento;

    @NotBlank(message = "Campo requerido")
    private String titulacao;

    @NotEmpty(message = "Deve ter pelo menos uma categoria")
    private Set<String> disponibilidades;

}
