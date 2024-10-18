package com.git.gestor_academico.mappers;

import com.git.gestor_academico.dtos.OrientadorDto;
import com.git.gestor_academico.models.Orientador;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrientadorMapper {

    OrientadorDto toDto(Orientador orientador);

    Orientador toDomain(OrientadorDto dto);

}
