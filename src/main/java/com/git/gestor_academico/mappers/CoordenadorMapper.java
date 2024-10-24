package com.git.gestor_academico.mappers;

import com.git.gestor_academico.dtos.request.CoordenadorRequestDTO;
import com.git.gestor_academico.dtos.response.CoordenadorResponseDTO;
import com.git.gestor_academico.models.Coordenador;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoordenadorMapper {

    CoordenadorResponseDTO toResponseDTO(Coordenador coordenador);

    Coordenador toDomain(CoordenadorRequestDTO coordenadorRequestDTO);

}
