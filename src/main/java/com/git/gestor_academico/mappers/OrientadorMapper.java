package com.git.gestor_academico.mappers;

import com.git.gestor_academico.dtos.response.OrientadorResponseDTO;
import com.git.gestor_academico.dtos.request.OrientadorRequestDTO;
import com.git.gestor_academico.models.Orientador;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrientadorMapper {

    OrientadorResponseDTO toDto(Orientador orientador);

    Orientador toDomain(OrientadorRequestDTO requestDTO);

}
