package com.git.gestor_academico.mappers;

import com.git.gestor_academico.dtos.request.AlunoRequestDTO;
import com.git.gestor_academico.dtos.response.AlunoResponseDTO;
import com.git.gestor_academico.dtos.response.AlunoTccResponseDTO;
import com.git.gestor_academico.models.Aluno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    @Mapping(target = "curso.nome", source = "curso.nome")
    @Mapping(target = "tccId", source = "tcc.id")
    AlunoResponseDTO toResponseDto(Aluno aluno);

    Aluno toDomain(AlunoRequestDTO dto);

    AlunoTccResponseDTO toTccResponseDto(Aluno aluno);

}
