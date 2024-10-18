package com.git.gestor_academico.mappers;

import com.git.gestor_academico.dtos.AlunoDto;
import com.git.gestor_academico.models.Aluno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    AlunoDto toDto(Aluno aluno);

    Aluno toDomain(AlunoDto dto);

}
