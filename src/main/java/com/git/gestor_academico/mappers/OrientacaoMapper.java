package com.git.gestor_academico.mappers;

import com.git.gestor_academico.dtos.request.OrientacaoRequestDTO;
import com.git.gestor_academico.dtos.response.OrientacaoResponseDTO;
import com.git.gestor_academico.models.Orientacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrientacaoMapper {

    @Mapping(target = "orientadorMatricula", source = "orientador.matricula")
    @Mapping(target = "tccId", source = "tcc.id")
    OrientacaoResponseDTO toResponseDTO(Orientacao orientacao);

    Orientacao toDomain(OrientacaoRequestDTO orientacaoRequestDTO);

}
