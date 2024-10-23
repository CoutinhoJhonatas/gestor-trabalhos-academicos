package com.git.gestor_academico.mappers;

import com.git.gestor_academico.dtos.request.TccRequestDTO;
import com.git.gestor_academico.dtos.response.TccResponseDTO;
import com.git.gestor_academico.models.Tcc;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TccMapper {


    TccResponseDTO toResponseDTO(Tcc tcc);

    Tcc toDomain(TccRequestDTO dto);

}
