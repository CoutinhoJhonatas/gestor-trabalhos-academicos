package com.git.gestor_academico.mappers;

import com.git.gestor_academico.dtos.TccDto;
import com.git.gestor_academico.models.Tcc;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TccMapper {


    TccDto toDto(Tcc tcc);


    Tcc toDomain(TccDto dto);

}
