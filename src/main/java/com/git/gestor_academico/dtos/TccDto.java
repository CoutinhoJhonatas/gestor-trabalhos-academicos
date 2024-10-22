package com.git.gestor_academico.dtos;

import com.git.gestor_academico.dtos.response.AlunoResponseDTO;
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
    private List<AlunoResponseDTO> integrantes;
    private OrientadorDto orientador;

}
