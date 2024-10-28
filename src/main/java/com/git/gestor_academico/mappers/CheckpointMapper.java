package com.git.gestor_academico.mappers;

import com.git.gestor_academico.dtos.request.CheckpointRequestDTO;
import com.git.gestor_academico.dtos.request.OrientacaoRequestDTO;
import com.git.gestor_academico.dtos.response.CheckpointResponseDTO;
import com.git.gestor_academico.dtos.response.OrientacaoResponseDTO;
import com.git.gestor_academico.models.Checkpoint;
import com.git.gestor_academico.models.Orientacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CheckpointMapper {

    CheckpointResponseDTO toResponseDTO(Checkpoint checkpoint);

    Checkpoint toDomain(CheckpointRequestDTO checkpointRequestDTO);

}
