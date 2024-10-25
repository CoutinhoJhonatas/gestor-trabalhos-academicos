package com.git.gestor_academico.dtos.response;

import com.git.gestor_academico.dtos.OrientadorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TccResponseDTO {

    private Long id;
    private String titulo;
    private String linkDocs;
    private String resumoProposta;
    private OrientadorDto orientador;
    private CoordenadorResponseDTO coordenador;
    private List<AlunoTccResponseDTO> integrantes;

}
