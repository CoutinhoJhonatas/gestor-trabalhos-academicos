package com.git.gestor_academico.services;

import com.git.gestor_academico.dtos.request.CoordenadorRequestDTO;
import com.git.gestor_academico.dtos.response.CoordenadorResponseDTO;
import com.git.gestor_academico.mappers.CoordenadorMapper;
import com.git.gestor_academico.models.Coordenador;
import com.git.gestor_academico.repositorys.CoordenadorRepository;
import com.git.gestor_academico.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CoordenadorService {

    private final CoordenadorRepository coordenadorRepository;
    private final CoordenadorMapper coordenadorMapper;

    private static final String COORDENADOR_NAO_ENCONTRADO = "Coordenador com o ID %d não foi encontrado";

    @Transactional(readOnly = true)
    public List<CoordenadorResponseDTO> listarTodos() {
        List<Coordenador> coordenadores = coordenadorRepository.findAll();
        List<CoordenadorResponseDTO> coordenadoresDtos = new ArrayList<>();
        coordenadores.forEach(coordenador -> coordenadoresDtos.add(coordenadorMapper.toResponseDTO(coordenador)));
        return coordenadoresDtos;
    }

    @Transactional(readOnly = true)
    public CoordenadorResponseDTO buscarPorMatricula(Long matricula) {
        Coordenador coordenador = findById(matricula);
        return coordenadorMapper.toResponseDTO(coordenador);
    }

    @Transactional
    public CoordenadorResponseDTO salvar(CoordenadorRequestDTO coordenadorRequestDTO) {
        Coordenador coordenador = coordenadorMapper.toDomain(coordenadorRequestDTO);
        coordenador.setRole("COORDENADOR");
        coordenador.setAtivo(true);
        coordenador = coordenadorRepository.save(coordenador);
        return coordenadorMapper.toResponseDTO(coordenador);
    }

    @Transactional
    public CoordenadorResponseDTO atualizar(Long matricula, CoordenadorRequestDTO coordenadorRequestDTO) {
        Coordenador coordenador = findById(matricula);
        coordenador.setNome(coordenadorRequestDTO.getNome());
        coordenador.setTelefone(coordenadorRequestDTO.getTelefone());
        coordenador = coordenadorRepository.save(coordenador);
        return coordenadorMapper.toResponseDTO(coordenador);
    }

    @Transactional
    public void desativarCadastro(Long matricula) {
        Coordenador coordenador = findById(matricula);
        coordenador.setAtivo(false);
        coordenadorRepository.save(coordenador);
    }

    private Coordenador findById(Long matricula) {
        return coordenadorRepository.findById(matricula)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(COORDENADOR_NAO_ENCONTRADO, matricula)));
    }

}
