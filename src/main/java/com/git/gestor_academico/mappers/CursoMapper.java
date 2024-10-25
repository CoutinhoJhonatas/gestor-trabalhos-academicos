package com.git.gestor_academico.mappers;

import com.git.gestor_academico.dtos.request.CoordenadorRequestDTO;
import com.git.gestor_academico.dtos.request.CursoRequestDTO;
import com.git.gestor_academico.dtos.response.CoordenadorResponseDTO;
import com.git.gestor_academico.dtos.response.CursoResponseDTO;
import com.git.gestor_academico.models.Coordenador;
import com.git.gestor_academico.models.Curso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    CursoResponseDTO toResponseDTO(Curso curso);

    Curso toDomain(CursoRequestDTO cursoRequestDTO);

}
